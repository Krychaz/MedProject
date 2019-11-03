from django.db import models
from datetime import datetime
from prof.models import Doctor

class listing(models.Model):
  doctor = models.ForeignKey(Doctor, on_delete=models.DO_NOTHING)
  name = models.CharField(max_length=200)
  age = models.IntegerField(default=0)
  gender = models.CharField(max_length=20)
  ethnicity = models.CharField(max_length=50)
  height = models.IntegerField(default=0)
  weight = models.IntegerField(default=0)
  PAL = models.IntegerField(default=0)
  blood_p = models.IntegerField(default=0)
  AIPW = models.IntegerField(default=0)
  SIPW = models.IntegerField(default=0)
  smoker = models.BooleanField()
  hearthbeat = models.BooleanField()
  date = models.DateField(default=datetime.now, blank=true)
def __str__(self):
    return self.name