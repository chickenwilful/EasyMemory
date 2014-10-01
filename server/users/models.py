from django.db import models


class User(models.Model):
    username = models.CharField(max_length=20)
    password = models.CharField(max_length=100)
    gamecount = models.IntegerField(default=0)

    def __str__(self):
        return self.username


class Friend(models.Model):
    user = models.ForeignKey(User)
    name = models.CharField(max_length=100)
    relation = models.CharField(max_length=100)

    def __str__(self):
        return '{} ({})'.format(self.name, self.relation)
