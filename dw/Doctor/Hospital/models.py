from django.db import models

# Create your models here.
class Patients(models.Model):
    Name = models.CharField(max_length=30)
    Age = models.IntegerField(default=0)
    Gender = models.CharField(max_length=20)
    Phonenumber = models.IntegerField(default=0)
    Password = models.CharField(max_length=30)
    def __str__(self):
        return  self.Name

    def createPatients(pat,name,age,gender,phonenumber,password):
        patient = pat(Name = name,Age = age,Gender = gender,Phonenumber = phonenumber,Password = password)
        return  patient

class District(models.Model):
    Name = models.CharField(max_length=30)
    def __str__(self):
        return  self.Name

class Hospital(models.Model):
    Name = models.CharField(max_length=30)
    Location = models.CharField(max_length=30)
    District = models.ForeignKey("District",on_delete=models.CASCADE)
    Description = models.CharField(max_length=2000)
    def __str__(self):
        return self.Name

class Department(models.Model):
    Name = models.CharField(max_length=30)
    Info = models.CharField(max_length=2000)
    def __str__(self):
        return self.Name

class DepartmentList(models.Model):
    Department = models.ForeignKey("Department",on_delete=models.CASCADE)
    Hospital = models.ForeignKey("Hospital",on_delete=models.CASCADE)
    def __str__(self):
        return self.Hospital.Name


class DepartmentInfo(models.Model):
    Department = models.ForeignKey("Department",on_delete=models.CASCADE)
    Hospital = models.ForeignKey("Hospital",on_delete=models.CASCADE)
    Restnumber = models.IntegerField(default=0)
    Time = models.DateField()
    def __str__(self):
        return  self.Department.Name

class Doctor(models.Model):
    Hospital = models.ForeignKey("Hospital",on_delete=models.CASCADE)
    Name = models.CharField(max_length=30)
    Department = models.ForeignKey("Department",on_delete=models.CASCADE)
    Doctorid = models.IntegerField(default=0)
    Phonenumber = models.IntegerField(default=0)
    Password = models.CharField(max_length=30)
    def __str__(self):
        return  self.Name

class Register(models.Model):
    Hospital = models.ForeignKey("Hospital",on_delete=models.CASCADE)
    Department = models.ForeignKey("Department",on_delete=models.CASCADE)
    Patients = models.ForeignKey("Patients",on_delete=models.CASCADE)
    Time = models.DateField()
    def __str__(self):
        return  str(self.pk)
    def createRegister(reg,hospital,department,patients,time):
        register = reg(Hospital=hospital,Department=department,Patients=patients,Time=time)
        return register

class Record(models.Model):
    Register = models.ForeignKey("Register",on_delete=models.CASCADE)
    Doctor = models.ForeignKey("Doctor",on_delete=models.CASCADE)
    Patient = models.ForeignKey("Patients",on_delete=models.CASCADE)
    NP = models.CharField(max_length=30)
    Name = models.CharField(max_length=30)
    Info = models.CharField(max_length=2000)
    def __str__(self):
        return self.Name
    def createRecord(red,register,doctor,patient,np,name,info):
        record = red(Register=register,Doctor=doctor,Patient=patient,NP=np,Name=name,Info=info)
        return record

class Blog(models.Model):
    Author = models.ForeignKey("Patients",on_delete=models.CASCADE)
    Title = models.CharField(max_length=100)
    Text = models.CharField(max_length=3000)
    Time = models.DateTimeField(auto_now=True)
    def __str__(self):
        return self.Title
    def createBlog(blo,author,title,text):
        blog = blo(Author=author,Title=title,Text=text)
        return blog

class Comment(models.Model):
    Author = models.ForeignKey("Patients",on_delete=models.CASCADE)
    Blog = models.ForeignKey("Blog",on_delete=models.CASCADE)
    Text = models.CharField(max_length=1000)
    Time = models.DateTimeField(auto_now=True)
    def __str__(self):
        return self.Text
    def createComment(com,author,blog,text):
        comment = com(Author=author,Blog=blog,Text=text)
        return comment

class Expert(models.Model):
    Name = models.CharField(max_length=30)
    Phonenumber = models.IntegerField(default=0)
    Info = models.CharField(max_length=500)
    Password = models.IntegerField(default=0)
    def __str__(self):
        return self.Name

class Message(models.Model):
    Expert = models.ForeignKey("Expert",on_delete=models.CASCADE)
    Patients = models.ForeignKey("Patients",on_delete=models.CASCADE)
    Text = models.CharField(max_length=1000)
    Time = models.DateTimeField(auto_now=True)
    Tag = models.CharField(max_length=50)
    def __str__(self):
        return self.Text
    def createmessage(m,expert,patient,text,tag):
        message = m(Expert=expert,Patients=patient,Text = text,Tag=tag)
        return message


