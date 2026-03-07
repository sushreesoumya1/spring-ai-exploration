# Spring AI Exploration

A Spring Boot playground for experimenting with Spring AI features including chat models (OpenAI, Ollama, Gemma), RAG, vector search, streaming, multimodal AI (speech, image), observability, and evaluation.
1) OpenAI (via API key)
2) Ollama (local LLM runtime)
3) Gemma (via Docker Desktop)
4) Explore Prompt Templates and Stuffing
5) Chat Streaming
6) Add Conversation ID for the chats
7) Store chats using H2 DB
8) Limit chat history
9) Vector store setup 
10) RAG (using RetrievalAugmentationAdvisor)
11) RAG (with web-search capabilities)
12) MP3 to Text
13) Text to MP3
14) Text to Image
15) Prometheus and Grafana
16) Unit Test with Evaluators
17) To-Do: Tool Calling & MCP


## Prerequisites
- For OpenAI:
  - An OpenAI API key
- For Ollama:
  - Ollama installed and running locally (default API: http://localhost:11434/api)
- For Gemma:
  - Docker Desktop installed and docker model run ai/gemma3
