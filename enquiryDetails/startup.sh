#!/bin/bash
nohup java -jar target/enquiryDetails-0.0.1-SNAPSHOT.jar > logs/enquiry.txt 2>&1 & 
echo $! > pid.file
cat pid.file
