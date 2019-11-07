from django.shortcuts import render
from .models import listing 
# Create your views here.


def index(request):
  #listings = listing.objects.all()

  #context = {
    #'patients' : listings
  #}

  return render(request, 'listings/listings.html')

def listing(request):
  return render(request, 'listings/listing.html')

def search(request):
  return render(request, 'listings/search.html')
