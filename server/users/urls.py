from django.conf.urls import patterns, url

from users import views


urlpatterns = patterns(
    '',
    url(r'^create$', views.create, name='create'),
    url(r'^login$', views.login, name='login'),
    url(r'^list_friend$', views.list_friend, name='list_friend'),
    url(r'^add_friend$', views.add_friend, name='add_friend'),
)
