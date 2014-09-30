from users.models import User, Friend
from utils import *


def list_friend(request):
    try:
        username = request.POST['username']
    except KeyError:
        return json_invalid_request()

    try:
        user = User.objects.get(username=username)
    except User.DoesNotExist:
        return json_invalid_request()

    friends = Friend.objects.filter(user=user)

    friend_list = []
    for friend in friends:
        friend_list.append({
            'name': friend.name,
            'relation': friend.relation,
        })

    return json_success_response('', {
        'friends': friend_list
    })


def add_friend(request):
    try:
        username = request.POST['username']
        friend_name = request.POST['friend_name']
        friend_relation = request.POST['friend_relation']
    except KeyError:
        return json_invalid_request()

    try:
        user = User.objects.get(username=username)
    except User.DoesNotExist:
        return json_invalid_request()

    friend = Friend(user=user, name=friend_name, relation=friend_relation)
    friend.save()
    return json_success_response('Created friend')
