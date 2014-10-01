import random

from games.const import MAX_QUESTION, CHOICES_PER_QUESTION
from users.models import User, Friend
from utils import *


def game_next_step(request):
    try:
        username = request.POST['username']
        user = User.objects.get(username=username)
    except KeyError:
        return json_invalid_request()
    except User.DoesNotExist:
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
        friend_array = [{'name': friend.name,
                         'rel': friend.relation,
                         'img': friend.img.url if friend.img else ''} for friend in friends]

        if len(friend_array) < CHOICES_PER_QUESTION:
            return json_failed_response("You don't have enough friends to play")

        ids = []
        while len(ids) < CHOICES_PER_QUESTION:
            new_id = random.randint(0, len(friend_array) - 1)
            if new_id not in ids:
                ids.append(new_id)

        correct = random.randint(0, len(ids) - 1)

        return json_success_response('', {
            'choices': [friend_array[x] for x in ids],
            'correct': correct,
            'img': friend_array[correct]['img'],
            'finished': 0,
        })
