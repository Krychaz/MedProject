from django.contrib import admin

from .models import Doctor

admin.site.register(Doctor)

from .models import Insurance

admin.site.register(Insurance)