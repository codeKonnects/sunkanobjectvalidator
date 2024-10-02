# Java Field Validation Library

This library provides a simple and flexible way to validate Java object fields using annotations. It's designed to work with encrypted request strings that are deserialized into POJOs, allowing for field validation after the deserialization process.

## Features

- Annotation-based field validation
- Custom error messages
- Support for various data types including String, Number, Date, LocalDate, and LocalDateTime
- Extensible architecture for adding custom validators

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.2+
- GitHub account with access to the repository

### Installation

#### Using GitHub Packages

This library is distributed using GitHub Packages. To use it in your project, follow these steps:

1. Add the GitHub Packages repository to your `pom.xml`:

```xml
	<distributionManagement>
		<repository>
			<id>github</id>
			<name>GitHub Packages</name>
			<url>https://maven.pkg.github.com/codeKonnects/sunkanobjectvalidator</url>
		</repository>
	</distributionManagement>
```

Replace `OWNER` with the GitHub username or organization name, and `REPOSITORY` with the name of the repository containing this package.

2. Add the dependency to your `pom.xml`:

```xml
		<dependency>
			<groupId>sunkan.validator</groupId>
			<artifactId>objectvalidator</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
```

3. Authenticate to GitHub Packages:

Create or edit the `~/.m2/settings.xml` file to include your GitHub authentication:

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_PERSONAL_ACCESS_TOKEN</password>
    </server>
  </servers>
</settings>
```

Replace `YOUR_GITHUB_USERNAME` with your GitHub username and `YOUR_PERSONAL_ACCESS_TOKEN` with a GitHub personal access token that has the `read:packages` scope.

Now you can use the library in your project!

### Usage

1. Add annotations to your POJO fields:

```java
import sunkan.validator.annotation.*;
import java.time.LocalDate;

public class User {
    @NotNull(message = "Username cannot be null or blank")
    @Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters")
    private String username;

    @NotNull(message = "Email cannot be null or blank")
    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18, message = "Age must be at least {value}")
    @Max(value = 100, message = "Age must be at most {value}")
    private int age;

    @Future(message = "Subscription end date must be in the future")
    private LocalDate subscriptionEndDate;

    // Getters and setters...
}
```

2. Validate the object:

```java
User user = // ... deserialize from encrypted string
ValidationProcessor validator = new ValidationProcessor();
String errors = validator.validate(user);

if (!errors.isEmpty()) {
    System.out.println("Validation errors: " + errors);
} else {
    System.out.println("Validation passed");
}
```

## Available Annotations

- `@NotNull`: Ensures the field is not null or blank (for strings)
- `@Size`: Validates the length of strings, collections, maps, or arrays
- `@Pattern`: Validates string against a regular expression
- `@Min`: Validates that a number is at least the specified value
- `@Max`: Validates that a number is at most the specified value
- `@Email`: Validates email format
- `@Future`: Ensures a date is in the future
- `@Past`: Ensures a date is in the past

