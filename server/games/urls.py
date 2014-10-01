from django.conf.urls import patterns, url

from games import views


urlpatterns = patterns(
    '',
    url(r'^next_step$', views.game_next_step, name='next_step'),
)
