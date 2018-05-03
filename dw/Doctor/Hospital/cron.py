from Hospital.models import DepartmentInfo
from Hospital.views import changemanager
def clock():
    info = DepartmentInfo.objects.get(pk=1)
    info.Restnumber = info.Restnumber+1
    info.save()