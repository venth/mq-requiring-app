MQ requiring app
=================================

# Table of contents
1. Overview
2. [Scenarios] (Scenarios)
3. [Conclusions] (Conclusions)

# Overview

The goal of this poc is to investigate different configuration possibilities, which could help to make an application
 using mq services, even if a broker is absent. 

# Scenarios

* Application starts, when mq broker is missing

A message broker is down and the application start is requested. The application starts without troubles and is ready
to work

* All messages are passed wo mq broker, when it appears

For some of a reason, mq broker is down and the application is working. Which means also, that the application sends
messages to the broker. Since, the mq broker is missing application has to run undisturbed and pass the messages 
when the broker will reappear.

# Conclusions
