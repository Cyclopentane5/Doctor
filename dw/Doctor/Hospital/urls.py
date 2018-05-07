from django.conf.urls import url

from . import views

urlpatterns = [
    url('^$',views.index),
    url('^(\d+)/$',views.detail),
    url('^generals/$',views.General),
    url('^FrontPage/$', views.DepartmenttoHospital),
    url('^FrontPage/(\d+)/$', views.HospitalList),
    url('^FrontPage/(\d+)/(\d+)/$', views.RegisterInfo),
    url('^districts/$',views.Districtlist),
    url('^districts/(\d+)/$',views.DistricttoHospital),
    url('^districts/(\d+)/(\d+)/$',views.HospitaltoDepartment),
    url('^districts/(\d+)/(\d+)/(\d+)/$',views.departmentregister),
    url('^showsignup/$',views.showsignup),
    url('^signup/$',views.signup),
    url('^showplogin/$',views.showplogin),
    url('^showdlogin/$',views.showdlogin),
    url('^psuccess/$',views.plogin),
    url('^dsuccess/$',views.dlogin),
    url('^dsuccess/(\d+)/$', views.addnumber),
    url('^dsuccess/(\d+)/addsuccess/$', views.addsuccess),
    url('^pregister/$',views.pregister),
    url('^showregister/$',views.showregister),
    url('^showregister/(\d+)/$',views.cancelregister),
    url('^logout/$',views.logout),
    url('^manage/$',views.manage),
    url('^manage/(\d+)/$',views.addrecord),
    url('^manage/(\d+)/success/$',views.saddrecord),
    url('^SearchName/$',views.searchname),
    url('^SearchName/(\d+)/$',views.HospitalList),
    url('^SearchName/(\d+)/(\d+)/$',views.RegisterInfo),
    url('^showchangeinfo/$',views.showchangeinfo),
    url('^changeinfo/$',views.changeinfo),
    url('^verifycode/$',views.verifycode),
    url('^postblog/$',views.postblog),
    url('^showblog/$',views.showblog),
    url('^showpostblog/$',views.showpostblog),
    url('^showblog/(\d+)/$',views.comment),
    url('^showblog/(\d+)/postcomment/$',views.postcomment),
    url('^showblog/(\d+)/postcomment/success/$',views.showpostcomment),
    url('^searchblog/$',views.searchblog),
    url('^searchblog/(\d+)/$',views.comment),
    url('^searchblog/(\d+)/postcomment/$',views.postcomment),
    url('^searchblog/(\d+)/postcomment/success/$',views.showpostcomment),
    url('^ownblog/$',views.ownblog),
    url('^ownblog/(\d+)/view/$' ,views.comment),
    url('^ownblog/(\d+)/delete/$' ,views.deleteblog),
    url('^owncomments/$',views.owncomment),
    url('^owncomments/(\d+)/$',views.deletecomment),
    url('^consultexpert/$',views.consultexpert),
    url('^expertlogin/$',views.expertlogin),
    url('^esuccess/$',views.esuccess),
    url('^emessage/$',views.emessage),
    url('^consultexpert/(\d+)/$',views.chatboxpatient),
    url('^emessage/(\d+)/$',views.chatboxexpert),
    url('^consultexpert/(\d+)/chat/$',views.chatpatient),
    url('^emessage/(\d+)/chat/$',views.chatexpert),
]