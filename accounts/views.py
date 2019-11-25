from django.shortcuts import render
from django.shortcuts import redirect

def register(request):
  return render(request, 'accounts/register.html')

def login(request):
  return render(request, 'accounts/login.html')

def logout(request):
  return redirect('index')

def listings(request):
  return render(request, 'listings/login.html')