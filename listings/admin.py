from django.contrib import admin

from .models import listing

class ListingAdmin(admin.ModelAdmin):
  list_display = ('doctor', 'name', 'age', 'gender', 'date')
  list_display_links = ['doctor', 'name']

admin.site.register(listing, ListingAdmin)