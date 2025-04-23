
# Types of Prompts for Large Language Models (LLMs)

A categorized list of prompt types, ordered from simplest to most complex.

---

## 1. Direct Instruction Prompt

- **Description**: The simplest form of prompting. You give the model a clear command or question.
- **Usage**: Best for straightforward tasks like definitions, summaries, or single-turn Q&A.
- **Examples**:
  - "Summarize this article in one paragraph."
  - "What is the capital of Japan?"
  - "Translate 'Good morning' into French."

---

## 2. Fill-in-the-Blank Prompt

- **Description**: Prompts where the model completes a sentence or fills in missing parts.
- **Usage**: Good for auto-completion, language learning, or testing contextual understanding.
- **Examples**:
  - "The Eiffel Tower is located in ___."
  - "Once upon a time, there was a dragon who..."
  - "To be or not to be, that is the ____."

---

## 3. Few-Shot Prompting

- **Description**: You provide a few examples of input-output pairs to guide the model’s behavior.
- **Usage**: Useful when task instructions alone are insufficient.
- **Examples**:
  ```text
  Translate to Spanish:
  English: Hello → Spanish: Hola
  English: Thank you → Spanish: Gracias
  English: How are you → Spanish:
  ```

  ```text
  Classify sentiment:
  Review: "I love this product!" → Sentiment: Positive
  Review: "This is the worst." → Sentiment: Negative
  Review: "Not bad, could be better." → Sentiment:
  ```

---

## 4. Chain-of-Thought Prompting

- **Description**: Prompts that encourage the model to reason step-by-step to reach an answer.
- **Usage**: Ideal for math problems, logic puzzles, or any reasoning tasks.
- **Examples**:
  - "If there are 3 apples and you take away 1, how many do you have? Let's think step by step."
  - "John is taller than Mike. Mike is taller than Sarah. Who is the shortest? Explain your reasoning."

---

## 5. Role-Based Prompting

- **Description**: You assign the model a persona or role to shape its response style and context.
- **Usage**: Useful for simulations, creative writing, or professional tasks.
- **Examples**:
  - "You are a friendly barista. Greet a customer who's ordering coffee for the first time."
  - "Act as a lawyer explaining tenant rights to a client in plain English."
  - "Pretend you're a 1920s detective solving a mystery."

---

## 6. Multi-Turn / Conversational Prompting

- **Description**: The prompt includes a conversation history, allowing for more dynamic and context-aware interactions.
- **Usage**: Ideal for chatbots, tutoring systems, or interactive storytelling.
- **Examples**:
  ```text
  User: What's the best way to learn guitar?
  Assistant: Start with basic chords and simple songs.
  User: Which chords should I learn first?
  ```

  ```text
  User: Tell me a joke.
  Assistant: Why don't skeletons fight each other? They don't have the guts.
  User: Tell me another.
  ```

---

## 7. Retrieval-Augmented Prompting (RAG)

- **Description**: Combines external data retrieval with prompting to provide grounded, accurate responses.
- **Usage**: Used when model needs to reference up-to-date or specialized knowledge.
- **Examples**:
  - "Search documents for 'quantum computing' and summarize the key points."
  - "Given the latest earnings call transcript, what are the company’s growth strategies?"

---

## 8. Tool-Using Prompting

- **Description**: Prompts that direct the model to use external tools like calculators, search engines, or APIs.
- **Usage**: Advanced applications involving real-world action or external systems.
- **Examples**:
  - "Use the calculator to solve 3982 x 123."
  - "Search the web for the latest price of Bitcoin and report it."
  - "Call the weather API to get the forecast for New York tomorrow."

---

## 9. Programmatic or API-Based Prompting

- **Description**: Prompts embedded within code, dynamically generated or templated.
- **Usage**: Used in software systems where prompts are constructed based on user data or inputs.
- **Examples**:
  ```python
  prompt = f"Summarize the following text: {user_input}"
  ```
  - Used in chatbots, workflow automation, or app integrations with LLMs.

---

## 10. Agentic Prompting (Autonomous Agents)

- **Description**: Prompts that kick off self-directed behaviors where the model sets goals, plans, and acts iteratively.
- **Usage**: For complex tasks like research agents, coding assistants, or long-term task planners.
- **Examples**:
  - "You're an AI researcher. Find 3 promising approaches to reduce hallucinations in LLMs and write a report."
  - "You are a personal assistant. Plan a two-week trip to Japan based on the user's preferences and budget."
