import os
from django.db import models


class User(models.Model):
    username = models.CharField(max_length=20, unique=True)
    password = models.CharField(max_length=100)
    gamecount = models.IntegerField(default=0)

    def __str__(self):
        return self.username


def content_file_name(instance, filename):
    return os.path.join('friend_img', str(instance.user.id) + filename[filename.find('.'):])


class Friend(models.Model):
    user = models.ForeignKey(User)
    name = models.CharField(max_length=100)
    relation = models.CharField(max_length=100)
    img = models.FileField(upload_to=content_file_name)

    def __str__(self):
        return '{} ({})'.format(self.name, self.relation)
