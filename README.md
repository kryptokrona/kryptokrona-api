# Kryptokrona API

[![alpha](https://kotl.in/badges/alpha.svg)](https://kotlinlang.org/docs/components-stability.html)
[![build](https://img.shields.io/github/actions/workflow/status/kryptokrona/kryptokrona-api/deploy-cd.yml?branch=master)](https://github.com/kryptokrona/kryptokrona-api/actions/workflows/deploy-cd.yml) 
[![kotlin](https://img.shields.io/badge/kotlin-1.8.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![release](https://img.shields.io/github/v/release/kryptokrona/kryptokrona-api)](https://img.shields.io/github/v/release/kryptokrona/kryptokrona-api)
[![license](https://img.shields.io/badge/License-BSD_3--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)
[![discord](https://img.shields.io/discord/562673808582901793?label=discord)](https://discord.gg/VTgsTGS9b7)

Kryptokrona is a decentralized blockchain from the Nordic based on CryptoNote, which forms the basis for Monero, among others. CryptoNote is a so-called “application layer” protocol further developed by TurtleCoin that enables things like: private transactions, messages and arbitrary data storage, completely decentralized.

Kryptokrona API in Kotlin, Ktor and Kryptokrona SDK for caching and processing data from the blockchain to provide faster access for services.

## Development Resources

- Web: https://kryptokrona.org
- Docs: https://docs.kryptokrona.org
- Mail: [mjovanc@icloud.com](mailto:mjovanc@icloud.com)
- GitHub: https://github.com/kryptokrona/kryptokrona-api
- Discord: https://discord.gg/VTgsTGS9b7
- Twitter: https://twitter.com/mjovanc
- OpenAPI Specification (Dev): https://petstore.swagger.io/?url=http://localhost:8080/api/openapi.json#/

## Getting Help

Are you having trouble with Kryptokrona API? We want to help!

- If you are upgrading, read the release notes for upgrade instructions and "new and noteworthy" features.

- Ask a question we monitor stackoverflow.com for questions tagged with kryptokrona-api. You can also chat with the community on Hugin or Discord.

- Report bugs with Kryptokrona API at https://github.com/kryptokrona/kryptokrona-api/issues.

## Reporting Issues

Kryptokrona API uses GitHub’s integrated issue tracking system to record bugs and feature requests. If you want to raise an issue, please follow the recommendations below:

- Before you log a bug, please search the issue tracker to see if someone has already reported the problem.

- If the issue doesn’t already exist, create a new issue.

- Please provide as much information as possible with the issue report. We like to know the Kryptokrona API version, operating system, and JVM version you’re using.

- If you need to paste code or include a stack trace, use Markdown. ``` escapes before and after your text.

- If possible, try to create a test case or project that replicates the problem and attach it to the issue.

## Deployment

Currently, we deploy to two servers, one for staging and one for production. The staging server is used for testing and replicating the production environment
and the production server is obviously used for production.

All endpoints are available on both servers. Check out the OpenAPI Specification for more information on how to use the API.

If you want to deploy the API yourself you can checkout the ansible directory for how to run the Ansible playbook.

### Staging

To use the staging server you have three options:

- Access the API: `https://stage.xkr.mjovanc.com/api/v{number}/{endppoint}`
- Access the OpenAPI Specification: https://petstore.swagger.io/?url=https://stage.xkr.mjovanc.com/api/openapi.json#/
- Access the monitor view: https://stage.xkr.mjovanc.com/monitor

### Production

To use the production server you have three options:

- Access the API: `https://xkr.mjovanc.com/api/v{number}/{endppoint}`
- Access the OpenAPI Specification: https://petstore.swagger.io/?url=https://xkr.mjovanc.com/api/openapi.json#/
- Access the monitor view: https://xkr.mjovanc.com/monitor

## Contribute

If you would like to contribute to this project there is two ways:

- Send a pull request
- Donate to XKR address

### Pull Request

We recommend using IntelliJ to work on this project.

We appreciate all contributions whether it be small changes such as documentation of source code to major improvement of code.
The easiest way is to make a fork and then make a pull request into our develop branch.

To make the PR go through make sure to include this information:

```
What does this PR do?

Why are these changes required?

This PR has been tested using (e.g. Unit Tests, Manual Testing):

Extra details?
```

NOTE: Remember to update existing diagrams if there is some bigger improvements of code so it's up to date with the implementation.

### Donate

XKR: `SEKReXXU9aJPiwjX2XkpbK8ACMWbUNXcYPxUVSiUYpNdhj8Z2snEy8CjjorZUNyswQNfzAmVWuGksU72Sf3Kq79Zd3fJWHq4Nyx`

## Contributors

The following contributors have either helped to start this project, have contributed
code, are actively maintaining it (including documentation), or in other ways
being awesome contributors to this project. **We'd like to take a moment to recognize them.**

[<img src="https://github.com/mjovanc.png?size=72" alt="mjovanc" width="72">](https://github.com/mjovanc)
[<img src="https://github.com/appelskrutt34.png?size=72" alt="appelskrutt34" width="72">](https://github.com/appelskrutt34)
[<img src="https://github.com/renovatebot.png?size=72" alt="mjovanc" width="72">](https://github.com/renovatebot)
[<img src="https://github.com/TechyGuy17.png?size=72" alt="TechyGuy" width="72">](https://github.com/TechyGuy17)

## Powered by

[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/kotlin.png" alt="Kotlin" height="72">](https://github.com/JetBrains/kotlin)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/ktor.png" alt="Ktor" height="72">](https://github.com/ktorio/ktor)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/svelte.png" alt="Svelte" height="72">](https://github.com/sveltejs/svelte)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/liquibase.png" alt="Liquibase" height="72">](https://github.com/liquibase/liquibase)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/postgresql.png" alt="PostgreSQL" height="72">](https://github.com/postgres/postgres)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/prometheus.png" alt="Prometheus" height="72">](https://github.com/prometheus/prometheus)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/ansible.png" alt="Ansible" height="72">](https://github.com/ansible/ansible)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/docker.png" alt="Docker" height="72">](https://github.com/docker)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/keycloak.png" alt="Keycloak" height="72">](https://github.com/keycloak/keycloak)
[<img src="https://github.com/kryptokrona/kryptokrona-api/blob/master/resources/poweredby/githubactions.png" alt="GitHub Actions" height="72">](https://github.com/actions)

## License

The 3-Clause BSD License.
