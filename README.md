![image](https://user-images.githubusercontent.com/487999/79708354-29074a80-82fa-11ea-80df-0db3962fb453.png)

## 예제 - 음식배달
대상 마이크로서비스 : 고객, 상점주, 라이더

## 분석/설계 : 이벤트 스토밍
![캡처](https://user-images.githubusercontent.com/55964235/219230867-c7d10180-697c-4649-8b1b-7db85e2bc523.PNG)

## 1. Saga(Pub / Sub)

![과제1 1](https://user-images.githubusercontent.com/55964235/219230917-9bac54e0-03d3-408e-a0ab-e180565c6cf2.PNG)
![과제1 2](https://user-images.githubusercontent.com/55964235/219230936-197e0067-d9d4-418d-8ba1-2ac5c227ad87.PNG)


## 2. CQRS 

![과제2 1](https://user-images.githubusercontent.com/55964235/219230975-f88b6ddd-9798-45c1-8196-da4a7542955f.PNG)
![과제2 2](https://user-images.githubusercontent.com/55964235/219230993-6e0fc9b1-5d46-4813-bea8-c00f12075c4e.PNG)
![과제2 3](https://user-images.githubusercontent.com/55964235/219231009-011ea861-bdf4-4c5d-bf2f-556436c38c32.PNG)
![과제2 4](https://user-images.githubusercontent.com/55964235/219231020-f0bdbf68-960b-4e9e-9aa4-95cbfb016c27.PNG)

## 3. Compensation / Correlation

![과제3 1](https://user-images.githubusercontent.com/55964235/219231030-e9cfeaef-66d3-4ab9-b925-963493209303.PNG)
![과제3 2](https://user-images.githubusercontent.com/55964235/219231038-f5bf92f4-3240-44a2-a29d-fdf2c88733de.PNG)

## 4. Deploy to EKS Cluster

EKS Cluster에 배포된 목록

![과제4](https://user-images.githubusercontent.com/55964235/219231070-af1f72d6-f389-426b-800f-f73bc4f2d01f.PNG)

## 5. Gateway & Service Router

Gateway & Service Router 설치된 목록

![과제5](https://user-images.githubusercontent.com/55964235/219231095-ebc6527f-1b38-45da-bd15-31d6824d99a0.PNG)

## 6. Autoscale (HPA)

1)siege 사용전

![과제6 1](https://user-images.githubusercontent.com/55964235/219231093-7f8cd5a5-411e-49a7-b341-8386ec4d539e.PNG)




2)siege사용후

![과제6 3](https://user-images.githubusercontent.com/55964235/219231096-76acc1d7-5a50-437b-ada9-e95be659af88.PNG)



3)pod 갯수 증가

![과제7](https://user-images.githubusercontent.com/55964235/219241564-0d144e96-2f3a-4123-a819-0145e989a033.PNG)


## Model
www.msaez.io/#/storming/426bb520c5d6524f4667e15c3b156e09

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- ordering
- store
- rider
- customer


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- ordering
```
 http :8088/orderLists id="id" foodId="foodId" address="address" status="status" customerId="customerId" 
 http :8088/payments id="id" orderId="orderId" cancel="cancel" 
```
- store
```
 http :8088/foodCookings id="id" status="status" foodId="foodId" orderId="orderId" options="options" storeId="storeId" customerId="customerId" 
```
- rider
```
 http :8088/deliveries id="id" status="status" orderId="orderId" address="address" 
```
- customer
```
 http :8088/orderStatuses id="id" status="status" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

