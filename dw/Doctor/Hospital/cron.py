from Hospital.models import DepartmentInfo,Comment,Blog
from Hospital.views import changemanager
def clock():
    info = DepartmentInfo.objects.get(pk=1)
    info.Restnumber = info.Restnumber+1
    info.save()

def delete():
    blogs = Blog.objects.filter(Text__icontains="fuck")
    for blog in blogs:
        blog.delete()
    comments = Comment.objects.filter(Text__icontains="fuck")
    for comment in comments:
        comment.delete()