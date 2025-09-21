# redis-nlp-doc-service

This is a small Java service I created to experiment with storing and processing text documents using Redis. The goal is to provide a simple backend that can save, fetch, and search documents, with the possibility to add basic NLP operations.

## Features
- Store and retrieve documents through REST endpoints  
- Simple keyword-based search  
- Integration with Redis for persistence and caching  
- Potential to extend with NLP tasks like tokenization or keyword extraction  

## Tech Stack
- **Language:** Java  
- **Framework:** Spring Boot (REST API)  
- **Database/Cache:** Redis  
- **Build Tool:** Maven (includes wrapper in repo)  

## Project Structure
- `src/` → source code  
- `pom.xml` → Maven build configuration  
- `.mvn/`, `mvnw`, `mvnw.cmd` → Maven wrapper files  

## Getting Started

### Prerequisites
- Java 17+  
- Redis installed and running locally  
- Maven (or use included wrapper)  

### Run
```bash
./mvnw spring-boot:run
```

## Test
```bash
./mvnw test
```

## Test Example
Example REST call to add a document:
```bash
curl -X POST http://localhost:8080/documents   -H "Content-Type: application/json"   -d '{"id": "doc1", "content": "This is a sample document about Redis and NLP."}'
```

Then search by keyword:
```bash
curl "http://localhost:8080/documents/search?query=Redis"
```

Response:
```json
[
  {
    "id": "doc1",
    "content": "This is a sample document about Redis and NLP."
  }
]
```

## Next Steps
In the future, I want to evolve this service into something more powerful by combining Redis with large language models (LLMs). The direction I’m exploring is **Automating Document Approvals with LLM-Based Rule Engines**.

The idea is to replace manual document approval processes, where external parties currently review and validate 10–20 financial or legal documents by hand. These reviews check for compliance with rules like presence of names, dates, digital signatures, or specific wording a process that is slow, error-prone, and inconsistent.

To address this, I designed a system that:
- Uses an LLM (e.g. GPT-5) to parse document content (PDF → text).  
- Evaluates the content against user-defined rule sets (e.g. “Document must contain a signature block and approval clause”).  
- Returns structured outputs such as:
```json
{
  "approval_status": "Approved",
  "reason": "All required fields and phrases present. Signed by both parties."
}
```
- Logs every decision and justification for auditability and traceability.  

On top of the LLM prompts, a **rule-engine layer** defines conditions such as:  
*If the phrase "approved by both parties" exists AND the document date is before today AND there is a signature section → APPROVED.*

Planned extensions include:
- Redis integration for caching evaluations.  
- REST API hooks into a document management system for automated workflows.  
