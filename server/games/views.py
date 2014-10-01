import random

from users.const import MAX_QUESTION
from users.models import User, Friend
from utils import *


def game_next_step(request):
    try:
        username = request.POST['username']
        user = User.objects.get(username=username)
    except KeyError, User.DoesNotExist:
        return json_invalid_request()

    if user.gamecount == MAX_QUESTION:
        user.gamecount = 0
        user.save()
        return json_success_response('You correctly answered {} question.', {
            'finished': 1
        })
    else:
        user.gamecount += 1
        user.save()

        friends = Friend.objects.filter(user=user)
        friend_array = [friend for friend in friends]

        id1 = random.randint(0, len(friend_array) - 1)
        id2 = id1
        while id2 == id1:
            id2 = random.randint(0, len(friend_array) - 1)

        correct = random.randint(0, 1)

        return json_success_response('', {
            'name1': friend_array[id1].name,
            'name2': friend_array[id2].name,
            'correct': correct,
            'finished': 0,
        })
