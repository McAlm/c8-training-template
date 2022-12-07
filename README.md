
## Exercise 10

Use a UserTask to resolve issues

### run 
```source /path/to/your/c8-saas-credentials```

```mvn clean install spring-boot:run```

### start a process instance
```zbctl publish message orderMessage  --variables "{\"orderTotal\": 45.99, \"customerId\": \"cust30\", \"cardNumber\": \"1234 5678\", \"cvc\": \"123\", \"expiryDate\": \"09/24\",\"orderId\":\"order-1\"}" --correlationKey "order-1"```