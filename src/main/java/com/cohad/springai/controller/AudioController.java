package com.cohad.springai.controller;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audio")
public class AudioController {

    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;
    private final OpenAiAudioSpeechModel speechModel;

    AudioController(OpenAiAudioTranscriptionModel transcriptionModel,
                    OpenAiAudioSpeechModel speechModel) {
        this.openAiAudioTranscriptionModel = transcriptionModel;
        this.speechModel = speechModel;
    }

    @GetMapping("/transcribe")
    String transcribe(@Value("classpath:SpringAI.mp3") Resource audioFile) {
        return openAiAudioTranscriptionModel.call(audioFile);
    }
    @GetMapping("/transcribe-options")
    String transcribeWithOptions(@Value("classpath:SpringAI.mp3") Resource audioFile) {
        var audioTranscriptionResponse = openAiAudioTranscriptionModel.call(new AudioTranscriptionPrompt(
                audioFile, OpenAiAudioTranscriptionOptions.builder()
                .prompt("Talking about Spring AI")
                .language("en")
                .temperature(0.5f)
                .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.VTT).build()));
        return audioTranscriptionResponse.getResult().getOutput();
    }

}
