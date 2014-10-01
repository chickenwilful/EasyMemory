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
            'img': friend.img.url if str(friend.img) else '',
            'id': friend.id,
        })

    return json_success_response('', {
        'friends': friend_list
    })


def add_friend(request):
    try:
        username = request.POST['username']
        friend_name = request.POST['friend_name']
        friend_relation = request.POST['friend_relation']
        friend_image = request.FILES['friend_image']
    except KeyError:
        return json_invalid_request()

    try:
        user = User.objects.get(username=username)
    except User.DoesNotExist:
        return json_invalid_request()

    friend = Friend(user=user, name=friend_name, relation=friend_relation, img=friend_image)
    friend.save()
    return json_success_response('Created friend')


def edit_friend(request):
    try:
        username = request.POST['username']
        friend_id = request.POST['friend_id']
        friend_name = request.POST['friend_name']
        friend_relation = request.POST['friend_relation']
        friend_image = request.FILES['friend_image']
    except KeyError:
        return json_invalid_request()

    try:
        user = User.objects.get(username=username)
        friend = Friend.objects.get(user=user, id=friend_id)
        friend.relation = friend_relation
        friend.img = friend_image
        friend.save()

        return json_success_response('Successfully updated user')
    except User.DoesNotExist:
        return json_invalid_request()
    except Friend.DoesNotExist:
        return json_invalid_request()
