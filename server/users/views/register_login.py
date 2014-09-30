from users.models import User
from utils import *


def create(request):
    # Check if this request is valid
    try:
        username = request.POST['username']
        password = make_password(request.POST['password'])
    except KeyError:
        return json_invalid_request()

    # Check if user does not already exists
    try:
        User.objects.get(username=username)
        return json_failed_response('Username already exists')
    except User.DoesNotExist:
        user = User(username=username, password=password)
        user.save()
        return json_success_response('User created')


def login(request):
    try:
        username = request.POST['username']
        password = make_password(request.POST['password'])
    except KeyError:
        return json_invalid_request()

    try:
        User.objects.get(username=username, password=password)
        return json_success_response('Successfully logged in')
    except User.DoesNotExist:
        return json_failed_response('Incorrect username or password: ')
