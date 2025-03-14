FROM python:3.10
WORKDIR /usr/src/app
COPY Main.py /usr/src/app/
CMD ["python", "Main.py"]
