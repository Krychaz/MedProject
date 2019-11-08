from django.shortcuts import render
# Create your views here.


def index(request):
  from listings.models import listing 
  listings = listing.objects.all()

  context = {
    'listings' : listings
  }
  return render(request, 'listings/listings.html', context)

def listing(request):
  return render(request, 'listings/listing.html')

def search(request):
  return render(request, 'listings/search.html')
