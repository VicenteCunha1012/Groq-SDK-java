# Groq AI Java SDK
A simple Java SDK for the Groq AI API, created to provide Java support where only JavaScript and Python SDKs are available.

Currently is incomplete as it doesn't support nearly as many features as the official sdks.

To create an instance of a ``GroqClient`` you just pass it the api key in the constructor.

```java
GroqClient client = new GroqClient(API_KEY);
```

Then you can use the ``singleQuery`` functions to get a response like mentioned in the ``ExampleUsage.java`` file.

It uses the jackson-databind library from jackson-core.

## Installation
Copy `GroqClient.java` into your source code and add the following dependency to your `pom.xml`.

```
  <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.18.1</version>
        </dependency>

```
## License
This project is licensed under the GNU General Public License v3.0 (GPL-3.0). This means that while you are free to use, modify, and distribute the code, any derived work must also be open source and licensed under GPL-3.0. 

See the full [LICENSE](./LICENSE) for details.


Enjoy!

