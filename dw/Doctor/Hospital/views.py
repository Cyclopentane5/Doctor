from django.shortcuts import render
# Create your views here.
from django.http import HttpResponse,HttpResponseRedirect
from django.utils.timezone import now, timedelta

def index(request):
    return render(request,'Hospital/index.html')

from Hospital.models import Hospital,Patients,Doctor,Register,Record,Department,DepartmentList,DepartmentInfo,District,Blog,Comment,Expert,Message,Advise
from django.http import JsonResponse

def General(request):
    info = request.session.get('k1',0)
    a=""
    if info  == 0:
        a="Guest"
    else:
        patient = Patients.objects.get(Phonenumber = info)
        a = patient.Name
    return render(request,'Hospital/General.html',{"name":a})

def DepartmenttoHospital(request):
    DepartmentL = Department.objects.all()
    return render(request,'Hospital/FrontPage.html',{"departments":DepartmentL})

def HospitalList(request,Department):
    Hospitallist = DepartmentList.objects.filter(Department__pk=Department)
    return render(request,'Hospital/Hospitallist.html',{"hospitals":Hospitallist})

def RegisterInfo(request,Department,Hospital):
    date = now().date() + timedelta(days=0)
    DepartmentInfoD = DepartmentInfo.objects.filter(Department__pk=Department).filter(Hospital__pk=Hospital).filter(Time__gte=date)
    a = DepartmentInfoD.values_list('pk',flat=True)
    x=0
    for value in a:
        x=value
    request.session['k2']=x

    return render(request,'Hospital/Departmentinfo.html',{"infos":DepartmentInfoD})

def Districtlist(request):
    DistrictList  = District.objects.all()
    return render(request,'Hospital/Districts.html',{"districts":DistrictList})

def DistricttoHospital(request,District):
    HospitalList = Hospital.objects.filter(District=District)
    return render(request,'Hospital/DistrictHospital.html',{"hospitals":HospitalList})

def HospitaltoDepartment(request,District,Hospital):
    DepartmentList1 = DepartmentList.objects.filter(Hospital__pk=Hospital)
    return render(request,'Hospital/DepartmentHospital.html',{"departments":DepartmentList1})

def departmentregister(request,District,Hospital,Department):
    date = now().date() + timedelta(days=0)
    DepartmentInfoD = DepartmentInfo.objects.filter(Department__pk=Department).filter(Hospital__pk=Hospital).filter(Time__gte=date)
    a = DepartmentInfoD.values_list('pk',flat=True)
    x=0
    for value in a:
        x = value
    request.session['k2']=x
    return render(request, 'Hospital/Departmentinfo.html', {"infos": DepartmentInfoD})

def signup(request):
    name = request.POST.get("name")
    age = request.POST.get("age")
    gender = request.POST.get("gender")
    phonenumber = request.POST.get("phonenumber")
    password1 = request.POST.get("password1")
    password2 = request.POST.get("password2")
    verify = request.POST.get("verify")
    if password1==password2 and verify==request.session.get("verifycode",0) and phonenumber.isdigit():
        phonenumber = int(phonenumber)
        pat = Patients
        patient = pat.createPatients(pat,name,age,gender,phonenumber,password1)
        patient.save()
        return render(request,'Hospital/success.html')
    else:
        return render(request,'Hospital/sign up.html')

def showsignup(request):
    return render(request,'Hospital/sign up.html')

def showplogin(request):
    if request.session.get('k1',0)==0:
        return render(request,'Hospital/Plogin.html')
    else:
        return HttpResponseRedirect('/psuccess/')

def showdlogin(request):
    if request.session.get('k3',0)==0:
        return render(request,'Hospital/Dlogin.html')
    else:
        return HttpResponseRedirect('/dsuccess/')

def plogin(request):
    if request.session.get('k1',0)==0:
        phonenumber = request.POST.get("phonenumber")
        if phonenumber.isdigit()==False:
            return render(request,'Hospital/false2.html')
        phonenumber = int(phonenumber)
        password = request.POST.get("password")
        verify = request.POST.get("verify")
        patients = Patients.objects.values_list('Password',flat=True).filter(Phonenumber = phonenumber)
        a=0
        for patient in patients:
            a = patient
        if a==password and verify==request.session.get('verifycode',0):
            request.session['k1']=phonenumber
            request.session['k3'] = 0
            request.session['k4'] = 0
            request.session.set_expiry(900)
            return render(request,'Hospital/psuccess.html')
        else:
            return render(request,'Hospital/false2.html')
    else:
        return render(request,'Hospital/psuccess.html')

def dlogin(request):
    if request.session.get('k3',0)==0:
        phonenumber = request.POST.get("phonenumber")
        password = request.POST.get("password")
        verify = request.POST.get("verify")
        if phonenumber.isdigit() == False:
            return render(request, 'Hospital/false.html')
        phonenumber=int(phonenumber)
        Doctor1 = Doctor.objects.values_list('Password',flat=True).filter(Phonenumber = phonenumber)
        a=0
        for doctor in Doctor1:
            a=doctor
        if a==password and verify==request.session.get('verifycode',0):
            request.session['k3']=phonenumber
            request.session['k1'] = 0
            request.session['k4'] = 0
            request.session.set_expiry(900)
            doctor = Doctor.objects.get(Phonenumber = request.session.get('k3',None))
            department = doctor.Department
            hospital = doctor.Hospital
            departmentinfos = DepartmentInfo.objects.filter(Department=department).filter(Hospital=hospital)
            return render(request,'Hospital/dsuccess.html',{"departmentinfos":departmentinfos})
        else:
            return render(request,'Hospital/false.html')
    else:
        doctor  = Doctor.objects.get(Phonenumber = request.session.get('k3',0))
        department = doctor.Department
        hospital = doctor.Hospital
        departmentinfos =DepartmentInfo.objects.filter(Department=department).filter(Hospital=hospital)
        return render(request,'Hospital/dsuccess.html',{"departmentinfos":departmentinfos})

def pregister(request,a,b,num):
    if request.session.get('k1',0)==0:
        return render(request, 'Hospital/false5.html')
    DepartmentInfo1 = DepartmentInfo.objects.get(pk=num)
    DepartmentInfo1.Restnumber = DepartmentInfo1.Restnumber-1
    DepartmentInfo1.save()
    Patient = Patients.objects.get(Phonenumber=request.session.get('k1',None))
    Hospital = DepartmentInfo1.Hospital
    Department = DepartmentInfo1.Department
    Time = DepartmentInfo1.Time
    reg = Register
    register = reg.createRegister(reg,Hospital,Department,Patient,Time)
    register.save()
    return render(request,'Hospital/success.html')

def pregister1(request,a,b,c,num):
    if request.session.get('k1',0)==0:
        return render(request, 'Hospital/false5.html')
    DepartmentInfo1 = DepartmentInfo.objects.get(pk=num)
    DepartmentInfo1.Restnumber = DepartmentInfo1.Restnumber-1
    DepartmentInfo1.save()
    Patient = Patients.objects.get(Phonenumber=request.session.get('k1',None))
    Hospital = DepartmentInfo1.Hospital
    Department = DepartmentInfo1.Department
    Time = DepartmentInfo1.Time
    reg = Register
    register = reg.createRegister(reg,Hospital,Department,Patient,Time)
    register.save()
    return render(request,'Hospital/success.html')

def showregister(request):
    Patient = Patients.objects.get(Phonenumber = request.session.get('k1',None))
    registerlist = Register.objects.filter(Patients=Patient)
    return render(request,'Hospital/showregister.html',{"registers":registerlist})

def cancelregister(request,num):
    register = Register.objects.get(pk=num)
    Hospital=register.Hospital
    Department=register.Department
    Time=register.Time
    registerdetail = DepartmentInfo.objects.filter(Hospital=Hospital).filter(Department=Department).filter(Time=Time)
    for a in registerdetail:
        a.Restnumber = a.Restnumber+1
        a.save()
    register.delete()
    return render(request,'Hospital/cancelregister.html')

def logout(request):
    request.session['k1']=0
    request.session['k2']=0
    request.session['k3']=0
    request.session['k4']=0
    return render(request,'Hospital/logout.html')

def manage(request):
    date = now().date() + timedelta(days=0)
    doctor = Doctor.objects.get(Phonenumber = request.session.get('k3',None))
    Department  =doctor.Department
    Hospital = doctor.Hospital
    registerlist = Register.objects.filter(Department=Department).filter(Hospital=Hospital).filter(Time=date)
    return render(request,'Hospital/Manage.html',{"registers":registerlist})

def addrecord(request,num):
    register = Register.objects.filter(pk=num)
    return render(request,'Hospital/addrecord.html',{"registers":register})

def saddrecord(request,num):
    register = Register.objects.get(pk=num)
    doctor = Doctor.objects.get(Phonenumber=request.session.get('k3',None))
    patient = register.Patients
    np = request.POST.get("np")
    name = request.POST.get("name")
    info = request.POST.get("info")
    red = Record
    record = red.createRecord(red,register,doctor,patient,np,name,info)
    record.save()
    return render(request,'Hospital/success2.html')

def check(request,num):
    register = Register.objects.get(pk=num)
    patient = register.Patients
    records = Record.objects.filter(Patient=patient)
    return render(request,'Hospital/check.html',{"records":records})


def addnumber(request,num):
   return render(request,'Hospital/addregister.html')

def addsuccess(request,num):
    number  =request.POST.get("number")
    number  =int(number)
    departmentinfo  = DepartmentInfo.objects.get(pk=num)
    departmentinfo.Restnumber=departmentinfo.Restnumber+number
    departmentinfo.save()
    return render(request,'Hospital/success2.html')

def searchname(request):
    name = request.POST.get("name")
    departmentlist = Department.objects.filter(Info__icontains=name)
    return render(request,'Hospital/FrontPage.html',{"departments":departmentlist})

def changeinfo(request):
    patient = Patients.objects.get(Phonenumber = request.session.get('k1',0))
    patient.Name = request.POST.get("name")
    patient.Age = request.POST.get("age")
    patient.Gender = request.POST.get("gender")
    patient.Phonenumber = request.POST.get("phonenumber")
    password1 = request.POST.get("password1")
    password2 = request.POST.get("password2")
    if password1==password2:
        patient.Password = password1
        patient.save()
        return render(request,'Hospital/success.html')
    else:
        HttpResponseRedirect('/showchangeinfo/')

def showchangeinfo(request):
    return render(request,'Hospital/showchangeinfo.html')

def postblog(request):
    return render(request,'Hospital/postblog.html')

def showblog(request):
    blogs = Blog.objects.all().order_by("Time")
    return render(request,'Hospital/Blog.html',{"blogs":blogs})

def showpostblog(request):
    author = Patients.objects.get(Phonenumber=request.session.get('k1',0))
    title = request.POST.get("title")
    text = request.POST.get("text")
    blo = Blog
    blog = blo.createBlog(blo,author,title,text)
    blog.save()
    return render(request,'Hospital/postsuccess.html')

def comment(request,num):
    comments = Comment.objects.filter(Blog=num).order_by("Time")
    return render(request,'Hospital/comment.html',{"comments":comments})

def deleteblog(request,num):
    comments = Comment.objects.filter(Blog=num)
    blog = Blog.objects.get(pk=num)
    for comment in comments:
        comment.delete()
    blog.delete()
    return render(request,'Hospital/deletesuccess.html')

def owncomment(request):
    comments = Comment.objects.filter(Author__Phonenumber = request.session.get('k1',0))
    return render(request,'Hospital/owncomments.html',{"comments":comments})

def deletecomment(request,num):
    comments = Comment.objects.get(pk=num)
    comments.delete()
    return render(request,'Hospital/deletesuccess.html')

def searchblog(request):
    text = request.POST.get("text")
    blogs = Blog.objects.filter(Text__icontains=text)
    titles = Blog.objects.filter(Title__icontains=text)
    comments = Comment.objects.filter(Text__icontains=text)
    value=""
    if len(comments)==0 and len(blogs)==0 and len(titles)==0:
        value="No result"
    return render(request,'Hospital/Blog.html',{"blogs":blogs,"titles":titles,"comments":comments,"value":value})

def ownblog(request):
    patient = Patients.objects.get(Phonenumber=request.session.get('k1',0))
    blogs = Blog.objects.filter(Author = patient)
    return render(request,'Hospital/ownblog.html',{"blogs":blogs})

def postcomment(request,num):
    return render(request,'Hospital/postcomment.html')

def showpostcomment(request,num):
    author = Patients.objects.get(Phonenumber=request.session.get('k1', 0))
    blog = Blog.objects.get(pk=num)
    text = request.POST.get("text")
    com1  = Comment
    comment1 = com1.createComment(com1,author,blog,text)
    comment1.save()
    return render(request,'Hospital/postsuccess.html')

def consultexpert(request):
    experts = Expert.objects.all()
    return render(request,'Hospital/consultexpert.html',{"experts":experts})

def expertlogin(request):
    if request.session.get('k4',0)==0:
        return render(request,'Hospital/expertlogin.html')
    else:
        return HttpResponseRedirect('/esuccess/')

def esuccess(request):
    if request.session.get('k4',0)==0:
        phonenumber = request.POST.get("phonenumber")
        password = request.POST.get("password")
        verify = request.POST.get("verify")
        if phonenumber.isdigit() == False:
            return render(request, 'Hospital/false3.html')
        phonenumber = int(phonenumber)
        experts  = Expert.objects.values_list('Password',flat = True).filter(Phonenumber = phonenumber)
        a=0
        for expert in experts:
            a=expert
        if a==password and verify==request.session.get('verifycode',0):
            request.session['k4']=phonenumber
            request.session['k1'] = 0
            request.session['k3'] = 0
            request.session.set_expiry(900)
            return render(request,'Hospital/esuccess.html')
        else:
            return render(request,'Hospital/false3.html')
    else:
        return render(request,'Hospital/esuccess.html')

def emessage(request):
    messages = Message.objects.filter(Expert__Phonenumber=request.session.get('k4',0)).values("Patients__Name","Patients").distinct()
    return render(request,'Hospital/emessage.html',{"messages":messages})

def chatboxpatient(request,num):
    return render(request,'Hospital/chatboxpatient.html')

def chatboxexpert(request,num):
    return render(request,'Hospital/chatboxexpert.html')

def chatpatient(request,num):
    expert = Expert.objects.get(pk=num)
    patient = Patients.objects.get(Phonenumber = request.session.get('k1',0))
    messages1 = Message.objects.filter(Expert=expert).filter(Patients=patient).order_by("Time")
    list1=[]
    for message in messages1:
        list1.append([message.Tag,message.Text])
    return JsonResponse({"data":list1,})

def chatexpert(request,num):
    patient = Patients.objects.get(pk=num)
    expert = Expert.objects.get(Phonenumber = request.session.get('k4',0))
    messages1 = Message.objects.filter(Expert=expert).filter(Patients=patient).order_by("Time")
    list1 = []
    for message in messages1:
        list1.append([message.Tag,message.Text])
    return JsonResponse({"data": list1})

def psendmessage(request,num):
    expert = Expert.objects.get(pk=num)
    patient = Patients.objects.get(Phonenumber=request.session.get('k1', 0))
    print()
    text = request.POST.get("text")
    print(text)
    tag = "Patients: "
    mes = Message
    message = mes.createmessage(mes,expert,patient,text,tag)
    message.save()
    a=""
    a = "/consultexpert/"+str(num)
    return HttpResponseRedirect(a)

def esendmessage(request,num):
    patient = Patients.objects.get(pk=num)
    expert = Expert.objects.get(Phonenumber=request.session.get('k4', 0))
    print(expert)
    text = request.POST.get("text")
    print(text)
    tag = "Expert: "
    mes = Message
    message = mes.createmessage(mes, expert, patient, text, tag)
    message.save()
    a=""
    a = "/emessage/" + str(num)
    return HttpResponseRedirect(a)

def advise(request):
    return render(request,'Hospital/advise.html')

def getadvise(request):
    advise = request.POST.get("advise")
    verify = request.POST.get("verify")
    if verify==request.session.get('verifycode',0):
        ad = Advise
        advise = ad.createadvise(ad,advise)
        advise.save()
        return render(request,'Hospital/success.html')
    else:
        return render(request,'Hospital/false4.html')

def viewrecord(request,num):
    return render(request,'Hospital/viewrecord.html')

def showviewrecord(request,num):
    patient = Patients.objects.get(pk=num)
    password = request.POST.get("password")
    if patient.Password==password:
        records = Record.objects.filter(Patient=patient)
        return render(request,'Hospital/check1.html',{"records":records})
    return render(request,'Hospital/false6.html')

def verifycode(request):
    from PIL import Image,ImageDraw,ImageFont
    import random

    bgcolor = (random.randrange(20,100),random.randrange(20,100),random.randrange(20,100))
    width = 100
    height = 50
    im = Image.new('RGB',(width,height),bgcolor)
    draw = ImageDraw.Draw(im)

    for i in range(0,100):
        xy = (random.randrange(0,width),random.randrange(0,height))
        fill = (random.randrange(0,255),255,random.randrange(0,255))
        draw.point(xy,fill=fill)

    str = '123456789QWERTYUASDFGHJKZXCVBNMqwertyuasdfghjkzxcvbnm'

    rand_str = ''
    for i in range(0,4):
        rand_str += str[random.randrange(0,len(str))]
    font = ImageFont.truetype("arial.ttf",30)

    fontcolor1 = (255, random.randrange(0, 255), random.randrange(0, 255))
    fontcolor2 = (255, random.randrange(0, 255), random.randrange(0, 255))
    fontcolor3 = (255, random.randrange(0, 255), random.randrange(0, 255))
    fontcolor4 = (255, random.randrange(0, 255), random.randrange(0, 255))

    draw.text((5, 2), rand_str[0], font=font, fill=fontcolor1)
    draw.text((25, 2), rand_str[1], font=font, fill=fontcolor2)
    draw.text((50, 2), rand_str[2], font=font, fill=fontcolor3)
    draw.text((75, 2), rand_str[3], font=font, fill=fontcolor4)

    del draw

    request.session['verifycode'] = rand_str
    import io
    buf = io.BytesIO()
    im.save(buf,'png')
    return HttpResponse(buf.getvalue(),'image/png')


class changemanager():
    pass