from django.contrib import admin

# Register your models here.
from .models import Hospital,Doctor,Department,Patients,Register,Record,District,DepartmentInfo,DepartmentList,Blog,Comment,Expert,Message
class showDoctor(admin.ModelAdmin):
    list_display = ['pk','Hospital','Name','Department','Doctorid','Phonenumber']
    list_filter = ['Name']
    search_fields =['Name']

class showHospital(admin.ModelAdmin):
    list_display = ['pk','Name','Location','District','Description']
    list_filter = ['Name']
    search_fields = ['Name']

class showDepartment(admin.ModelAdmin):
    list_display = ['pk','Name','Info']
    list_filter = ['Name']
    search_fields = ['Name']

class showDepartmentList(admin.ModelAdmin):
    list_display = ['pk','Hospital','Department']
    list_filter = ['Hospital']
    search_fields = ['Hospital']

class showDepartmentInfo(admin.ModelAdmin):
    list_display = ['pk','Department','Hospital','Restnumber','Time']
    list_filter = ['Department']
    search_fields = ['Department']

class showDistrict(admin.ModelAdmin):
    list_display = ['pk','Name']
    list_filter = ['Name']
    search_fields = ['Name']

class showPatients(admin.ModelAdmin):
    list_display = ['pk','Name','Age','Gender','Phonenumber']
    list_filter = ['Name']
    search_fields = ['Name']

class showRegister(admin.ModelAdmin):
    list_display = ['pk','Hospital','Department','Patients','Time']
    list_filter = ['Patients']
    search_fields = ['Patients']

class showRecord(admin.ModelAdmin):
    list_display = ['pk','Register','Doctor','Patient','NP','Name','Info']
    list_filter = ['Register']
    search_fields = ['Register']

class showBlog(admin.ModelAdmin):
    list_display = ['pk', 'Author', 'Title', 'Text', 'Time']
    list_filter = ['Title']
    search_fields = ['Title']

class showComment(admin.ModelAdmin):
    list_display = ['pk', 'Author', 'Blog', 'Text', 'Time']
    list_filter = ['Author']
    search_fields = ['Author']

class showExpert(admin.ModelAdmin):
    list_display = ['Name','Phonenumber','Info']
    list_filter = ['Name']
    search_fields = ['Name']



class showMessage(admin.ModelAdmin):
    list_display = ['Expert','Patients', 'Text', 'Time', 'Tag']
    list_filter = ['Text']
    search_fields = ['Text']


admin.site.register(District,showDistrict)
admin.site.register(Hospital,showHospital)
admin.site.register(Doctor,showDoctor)
admin.site.register(Department,showDepartment)
admin.site.register(DepartmentList,showDepartmentList)
admin.site.register(DepartmentInfo,showDepartmentInfo)
admin.site.register(Patients,showPatients)
admin.site.register(Register,showRegister)
admin.site.register(Record,showRecord)
admin.site.register(Blog,showBlog)
admin.site.register(Comment,showComment)
admin.site.register(Expert,showExpert)
admin.site.register(Message,showMessage)