# Job Portal ![CI status](https://img.shields.io/badge/build-passing-brightgreen.svg)

Job portal with REST services exposed.

## Installation

### Requirements
* Java 1.8
* Maven
* Docker

### Tech stack
* Java 1.8
* Spring
* Apache Kafka
* MySQL
* Docker

## ARCHITECTURE

### DB ARCHITECTURE :

Jobs (```title,salary,description,experience,availability,jobtype, language```)

Company (Company details like ``` name,city,state,zipCode,country```)

Skill(```skill's name```)

Job_skill(```mapping between job and skill```)

### JOB INSERT :

Whenever a new Job insert request comes the app will check if the company and the skill associated with the job is already present if yes, then it will map the present records to the job table else create a new one and map it in jobs table

### BULK JOB INSERT :

The app accepts an csv file which contains a list of jobs and sends it to kafka asynchronously. kafka consumer is also present in the app which consumes the data and adds it to the DB in the background.

### Search JOB :

The job search api returns the result based on the requested fields. The query is dynamically created based on the requested fields using CriteriaBuilder and JPA static metamodel.

## REST API'S EXPOSED :

## Create Job :

This endpoint is used to creates a job

#### URL : http://hostname:8050/job

###### Sample Request :

```
{
	"company": {
		"city": "string",
		"country": "India",
		"name": "Vignesh",
		"state": "Tamilnadu",
		"zipCode": "string"
	},
	"description": "Javadeveloper with 5 yrs of experience",
	"experience": "FRESHER",
	"jobtype": "DEVELOPER",
	"availability": "HOURLY",
	"language": "ENGLISH",
	"salary": 100,
	"skill": [{
		"name": "java"
	}, {
		"name": "j233"
	}],
	"title": "java"
}
```

### Bulk Job Upload :

#### URL : http://localhost:8050/bulkjob

Please find the sample csv file (BulkUploadTestFile.csv) in test/resources folder

Key : file form-data
Value : File(choose file)


### Search Job :

##### URL : http://localhost:8050/search/job


This endpoint is for job search.

###### Sample Response (anything can be missing ie) Search will be performed only on fields present in the request object)


```
{
	"salaryBound": {
		"salaryFrom": 50,
		"salaryTo": 150
	},
	"title": "java",
	"company": {
		"city": "string",
		"country": "India",
		"name": "Vignesh",
		"state": "Tamilnadu",
		"zipCode": "string"
	},
	"experience": "FRESHER",
	"jobtype": "DEVELOPER",
	"availability": "HOURLY",
	"language": "ENGLISH"

}
```
## Docker Images

Have created docker images . It can be run using the docker compose file attached (```docker-jobportal-stack.yaml```)

Set ```KAFKA_HOME``` to the ip-address of system before running the docker compose command

```
docker-compose -f docker-jobportal-stack.yaml up -d
```



## License
[MIT](https://choosealicense.com/licenses/mit/)