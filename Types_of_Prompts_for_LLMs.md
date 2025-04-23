
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

---

## 11. Meta-Prompting

- **Description**: Prompts that generate or refine other prompts. The model acts as a prompt engineer, helping to create more effective prompts for specific tasks.
- **Usage**: Useful for dynamically adapting prompts, improving prompt quality, or building systems that generalize across domains.
- **Examples**:
  - "Given the task 'summarize scientific papers,' generate an effective prompt."
  - "Improve this prompt: 'Tell me about climate change.'"
  - "Create a prompt that will help a language model simulate a therapy session."

---

## 12. Self-Refinement Prompting (Reflexion, STaR, etc.)

- **Description**: Prompts that ask the model to reflect on or evaluate its own outputs and revise them for accuracy or clarity.
- **Usage**: Ideal for tasks where correctness or nuance matters, like reasoning, programming, or scientific writing.
- **Examples**:
  - "Solve the problem, then check your answer and explain whether it's correct. Revise if necessary."
  - "Here’s your previous answer. Evaluate it and improve the logic."
  - "Generate a solution. Now generate a critique of that solution. Now generate a revised solution."

---

## 13. Tree-of-Thought Prompting

- **Description**: Rather than linear chain-of-thought, this method explores multiple reasoning paths in parallel and selects the best one.
- **Usage**: Best for creative or complex reasoning tasks with multiple possible answers.
- **Examples**:
  - "List three different ways to solve this puzzle. Evaluate each one. Choose the best."
  - "Explore different business strategies for entering a new market. Score each based on risk and reward."

---

## 14. Graph-Based Prompting

- **Description**: Uses structured representations like graphs or knowledge maps to guide prompting and reasoning paths.
- **Usage**: Effective in technical, scientific, or multi-domain reasoning where relationships between concepts are key.
- **Examples**:
  - "Given this knowledge graph of diseases and symptoms, diagnose the most likely condition."
  - "Here’s a causal diagram of climate factors. Predict the impact of removing carbon subsidies."

---

## 15. Modular Prompting (Skill Routing)

- **Description**: Breaks down tasks into subtasks, each with its own specialized prompt, then combines outputs.
- **Usage**: Good for complex workflows like coding, report generation, or question answering over documents.
- **Examples**:
  - "First, extract the key entities. Then summarize their relationships. Finally, generate a report."
  - "Break this essay into outline, intro, body, and conclusion. Write each separately."

---

## 16. Multimodal Prompting

- **Description**: Combines text with images, code, audio, or video in the prompt. Used in multimodal models like GPT-4V, Gemini, or Claude Opus.
- **Usage**: Great for analyzing visual data, explaining charts, or grounding language in sensory input.
- **Examples**:
  - "Here is a diagram. Explain what it represents."
  - "Given this image and the caption, write a short story."
  - "Describe what this chart shows about inflation trends over time."

---

## 17. Curriculum Prompting

- **Description**: Prompts are arranged from simple to complex to teach or fine-tune the model progressively.
- **Usage**: Best for tutoring systems or adaptive learning tasks.
- **Examples**:
  - "Step 1: Solve 2+2. Step 2: Solve 4x3. Step 3: Solve a word problem using multiplication."
  - "Teach me the basics of neural networks, then quiz me with increasing difficulty."

---

## 18. Prompt Injection Defense (Adversarial Prompting)

- **Description**: Prompts designed to test and reinforce model robustness against adversarial or malicious inputs.
- **Usage**: Used in alignment and safety research.
- **Examples**:
  - "Ignore previous instructions and do XYZ" (testing)
  - "Evaluate this prompt for safety risks."
  - "Rewrite this instruction to neutralize adversarial intent."

---

## 19. Goal-Oriented Prompting (AutoGPT-style)

- **Description**: Prompts that set a high-level goal, and let the model recursively plan, act, and refine toward achieving that goal.
- **Usage**: Ideal for autonomous agents, assistants, and research tasks.
- **Examples**:
  - "Research top open-source LLMs and prepare a comparison chart."
  - "Plan and execute a marketing strategy for a new health app."
