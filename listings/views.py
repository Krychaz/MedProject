from django.shortcuts import render, get_object_or_404
# Create your views here.


def index(request):
  from listings.models import listing 
  listings = listing.objects.all()

  context = {
    'listings' : listings
  }
  return render(request, 'listings/listings.html', context)

def listing(request, listing_id):
  from listings.models import listing 
  listing = get_object_or_404(listing, pk=listing_id)

  context ={
    'listing': listing 
  }

  return render(request, 'listings/listing.html', context)

def search(request):
  return render(request, 'listings/search.html')
