aws s3 cp s3://$BUCKET . --recursive
pip install -r requirements.txt
python deploy.py -e -v

