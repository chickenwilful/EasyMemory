import json
import hashlib

from django.http import HttpResponse


def json_response(response_data):
    return HttpResponse(json.dumps(response_data), content_type='application/json')


def json_invalid_request():
    return json_response({
        'success': 0,
        'message': 'Invalid request'
    })


def json_success_response(message, additional_data=None):
    data = {
        'success': 1,
        'message': message
    }
    if additional_data is not None:
        data = dict(data.items() + additional_data.items())
    return json_response(data)


def json_failed_response(message):
    return json_response({
        'success': 0,
        'message': message
    })


def make_password(str):
    return hashlib.md5(str).hexdigest()
