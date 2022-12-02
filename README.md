
## Exercise 8

Catch Exception and handle failures

### run 
```source /path/to/your/c8-saas-credentials```

```mvn clean install spring-boot:run```

### start a process instance
```zbctl publish message orderMessage --correlationKey "order-1" --variables "{\"orderId\":\"order-1\", \"orderTotal\": 45.99, \"customerId\": \"cust30\", \"cardNumber\": \"1234 5678\", \"cvc\": \"123\", \"expiryDate\": \"09/24\"}"```