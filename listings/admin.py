from django.contrib import admin

from .models import listing

class ListingAdmin(admin.ModelAdmin):
  list_display = ('name', 'age', 'gender', 'date')
  list_display_links = ['name']

admin.site.register(listing, ListingAdmin)