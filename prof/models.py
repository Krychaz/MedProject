from django.db import models

class Doctor(models.Model):
  name = models.CharField(max_length=200)
  phone = models.CharField(max_length=20)
  institution = models.CharField(max_length=200)
  def __str__(self):
    return self.name 

class Insurance(models.Model):
  name = models.CharField(max_length=200)
  phone = models.CharField(max_length=20)
  company = models.CharField(max_length=200)
  def __str__(self):
    return self.name 
