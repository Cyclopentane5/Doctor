from Hospital.models import DepartmentInfo,Comment,Blog
from Hospital.views import changemanager
from pandas import *
from numpy import *
from django.utils.timezone import now, timedelta

def createDataSet():
    postingList = [['my', 'dog', 'has', 'flea', 'problems', 'help', 'please'],
                   ['maybe', 'not', 'take', 'him', 'to', 'dog', 'park', 'stupid'],
                   ['my', 'dalmation', 'is', 'so', 'cute', 'I', 'love', 'him'],
                   ['stop', 'posting', 'stupid', 'worthless', 'garbage'],
                   ['mr', 'licks', 'ate', 'my', 'steak', 'how', 'to', 'stop', 'him'],
                   ['quit', 'buying', 'worthless', 'dog', 'food', 'stupid']]
    classVec = [0, 1, 0, 1, 0, 1]
    return postingList,classVec

def createVocaList(postingList):
    vocalSet=set([])
    for line in postingList:
        vocalSet=vocalSet|set(line)
    return vocalSet

def DataSet2Vec(vocalSet,input):
    vocalList=list(vocalSet)
    num_vocal=len(vocalList)
    num_lines=len(input)
    returnVec=zeros((num_lines,num_vocal))
    for i in range(num_lines):
        for j in range(len(input[i])):
            if input[i][j] in vocal_Set:
                t=input[i][j]
                returnVec[i][vocalList.index(t)]=1
            else:
                print("extra")
    return returnVec


def train_dataset(vocal_Vector,classVec):
    num_lines=len(vocal_Vector)
    num_words_in_line=len(vocal_Vector[0])
    count_abusive_list=zeros(num_words_in_line)
    count_no_abusive_list=zeros(num_words_in_line)
    num_Abusive=sum(classVec)
    pAbusive=num_Abusive/float(num_lines)
    for i in range(num_lines):
        if classVec[i]==1:
            count_abusive_list=count_abusive_list+vocal_Vector[i]
        else:
            count_no_abusive_list = count_no_abusive_list + vocal_Vector[i]
    list_pAbusive=-log((count_abusive_list+0.1)/(sum(count_abusive_list)+0.1))
    list_pnoAbusive=-log((count_no_abusive_list + 0.1) / (sum(count_no_abusive_list) + 0.1))

    return list_pAbusive,list_pnoAbusive,pAbusive


def classify(voc_to_list,list_pAbusive,list_pnoAbusive,pAbusive):
    num=len(voc_to_list[0])
    list_Abusive=voc_to_list*list_pAbusive
    list_no_Abusive=voc_to_list*list_pnoAbusive

    a=0.0
    b=0.0
    for i in range(num):
        if list_Abusive[0][i]>0:
            a=list_Abusive[0][i]+a
        if list_no_Abusive[0][i]>0:
            b=list_no_Abusive[0][i]+b
    if (a*pAbusive)<=(b*(1-pAbusive)):
        return 1
    else:
        return 0



postingList,classVec=createDataSet()

vocal_Set=createVocaList(postingList)

returnVec=DataSet2Vec(vocal_Set,postingList)

list_pAbusive,list_pnoAbusive,pAbusive=train_dataset(returnVec,classVec)


def clock():
    date = now().date() + timedelta(days=0)
    infos = DepartmentInfo.objects.all()
    for info in infos:
        info.Time = date
        info.Restnumber=info.Restnumber+50
        info.save()

def delete():
    date = now().date() + timedelta(days=0)
    blogs = Blog.objects.filter(Time__icontains = date)
    for blog in blogs:
        text = blog.Text
        text = text.split()
        input = text
        inputvector = DataSet2Vec(vocal_Set,input)
        x = classify(inputvector, list_pAbusive, list_pnoAbusive, pAbusive)
        if x==1:
            blog.Text="you blog has been clear"

    for blog in blogs:
        text = blog.Title
        text = text.split()
        input = text
        inputvector = DataSet2Vec(vocal_Set,input)
        x = classify(inputvector, list_pAbusive, list_pnoAbusive, pAbusive)
        if x==1:
            blog.Title = "you blog has been clear"

    comments = Comment.objects.filter(Time__icontains  =date)
    for comment in comments:
        text = comment.Text
        text = text.split()
        input = text
        inputvector = DataSet2Vec(vocal_Set, input)
        x = classify(inputvector, list_pAbusive, list_pnoAbusive, pAbusive)
        if x == 1:
            comment.Text ="you comment has been clear"