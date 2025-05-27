# Agentic AI Overview

Agentic AI refers to artificial intelligence systems that exhibit a degree of autonomy and goal-directed behavior. These systems can make decisions, plan actions, and adapt to changing environments in pursuit of objectives. Agentic AI is a cornerstone of building truly intelligent, interactive software systems.

---

## Table of Contents

* [What is Agentic AI?](#what-is-agentic-ai)
* [Types of Agentic AI](#types-of-agentic-ai)

  * [1. Reactive Agents](#1-reactive-agents)
  * [2. Deliberative Agents](#2-deliberative-agents)
  * [3. Hybrid Agents](#3-hybrid-agents)
  * [4. Learning Agents](#4-learning-agents)
* [Example Systems](#example-systems)
* [Code Examples](#code-examples)
* [Resources](#resources)

---

## What is Agentic AI?

Agentic AI systems operate based on the concept of an **agent**—an entity that perceives its environment through sensors and acts upon that environment using actuators. These systems can operate with a certain degree of independence and may be embedded with goals, plans, and learning capabilities.

Key characteristics:

* Autonomy
* Persistence over time
* Proactivity
* Reactivity
* Goal-orientation

---

## Types of Agentic AI

### 1. Reactive Agents

These agents respond directly to environmental stimuli without maintaining an internal state or reasoning.

**Example**: Simple robots that follow lines or avoid obstacles.

```python
# Example: A reactive agent that avoids obstacles
if sensor.detect_obstacle():
    robot.turn_left()
else:
    robot.move_forward()
```

---

### 2. Deliberative Agents

Deliberative agents use models of the world and logical reasoning to plan their actions.

**Example**: Chess-playing AI or planning-based robotics.

```python
# Pseudocode for planning
plan = planner.generate_plan(goal, world_model)
agent.execute(plan)
```

---

### 3. Hybrid Agents

Hybrid agents combine reactive and deliberative components. They can plan and respond in real-time.

**Example**: Autonomous vehicles.

---

### 4. Learning Agents

Learning agents can improve their performance over time based on past experiences.

**Example**: Reinforcement learning systems.

```python
# Basic reinforcement learning loop
for episode in episodes:
    state = env.reset()
    while not done:
        action = agent.select_action(state)
        next_state, reward, done = env.step(action)
        agent.learn(state, action, reward, next_state)
        state = next_state
```

---

## Example Systems

### 1. AutoGPT / BabyAGI

Agentic AI that strings together tasks, interacts with APIs, and iterates on feedback.

### 2. Robotics Systems

Autonomous drones, warehouse robots, and humanoid bots that act based on internal models and goals.

### 3. AI Companions / Assistants

Personalized digital agents that learn from user interaction and adapt accordingly (e.g., Replika, Character.AI).

---

## Code Examples

### LangChain Agent

```python
from langchain.agents import initialize_agent, AgentType
from langchain.llms import OpenAI
from langchain.tools import load_tools

llm = OpenAI(temperature=0)
tools = load_tools(["serpapi"])

agent = initialize_agent(tools, llm, agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION)
response = agent.run("What's the weather in San Francisco?")
```

### Stock Trading Agent (Simplified Example)

This example uses a moving average strategy. A more sophisticated agent could incorporate news sentiment analysis, reinforcement learning, and risk management policies.

```python
class TradingAgent:
    def __init__(self, strategy):
        self.strategy = strategy

    def decide(self, data):
        return self.strategy.evaluate(data)

class MovingAverageStrategy:
    def evaluate(self, data):
        short_ma = sum(data[-5:]) / 5
        long_ma = sum(data[-20:]) / 20
        if short_ma > long_ma:
            return "BUY"
        elif short_ma < long_ma:
            return "SELL"
        else:
            return "HOLD"

# Simulated market data
data = [100, 102, 105, 107, 110, 115, 117, 119, 121, 125,
        123, 120, 118, 116, 114, 112, 110, 108, 106, 104]

agent = TradingAgent(MovingAverageStrategy())
decision = agent.decide(data)
print("Trade Decision:", decision)
```

### Deep Reinforcement Learning-Based Trading Agent

Using libraries like `Stable-Baselines3` and environments from `FinRL`, agents can be trained using PPO, DQN, or A2C.

```python
from finrl.meta.env_stock_trading.env_stocktrading import StockTradingEnv
from stable_baselines3 import PPO
from finrl.agents.stablebaselines3.models import DRLAgent

# Create environment and train PPO agent
env = StockTradingEnv(config=env_config)
model = PPO("MlpPolicy", env, verbose=1)
model.learn(total_timesteps=10000)

# Evaluate agent
obs = env.reset()
done = False
while not done:
    action, _states = model.predict(obs)
    obs, rewards, done, info = env.step(action)
```

These agents are highly customizable and capable of managing stock, crypto, or even real estate portfolios using time series, sentiment, and macroeconomic data.

---

## Sector Applications

### Crypto

* Arbitrage bots using L2 exchanges
* DeFi portfolio agents that manage staking/yield farming
* Sentiment-based trading from Twitter or on-chain data

### Real Estate

* Agents that analyze listings, market trends, and construction data
* Automated valuation agents for flipping or investing
* Property management assistants with predictive maintenance and rental optimization

---

## Resources

* [Russell & Norvig - Artificial Intelligence: A Modern Approach](https://aima.cs.berkeley.edu/)
* [LangChain Documentation](https://docs.langchain.com/)
* [AutoGPT GitHub](https://github.com/Torantulino/Auto-GPT)
* [OpenAI Agents](https://platform.openai.com/docs/assistants/overview)
* [DeepMind - AlphaGo & MuZero Papers](https://deepmind.com/research)
* [Alpaca Trading API](https://alpaca.markets/docs/)
* [FinRL: Deep Reinforcement Learning for Automated Stock Trading](https://github.com/AI4Finance-Foundation/FinRL)
* [Stable-Baselines3 Docs](https://stable-baselines3.readthedocs.io/)

---

