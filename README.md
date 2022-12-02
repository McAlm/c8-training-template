
## Exercise 6

### run 
```source /path/to/your/c8-saas-credentials```

```mvn clean install spring-boot:run```

### start a process instance
```zbctl create instance payment --variables "{\"orderTotal\": 45.99, \"customerId\": \"cust30\", \"cardNumber\": \"1234 5678\", \"cvc\": \"123\", \"expiryDate\": \"09/24\"}"```