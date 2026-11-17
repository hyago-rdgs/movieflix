package br.com.hyagorodrigues.movieflix.controller;

import br.com.hyagorodrigues.movieflix.controller.request.StreamingRequest;
import br.com.hyagorodrigues.movieflix.controller.response.StreamingResponse;
import br.com.hyagorodrigues.movieflix.entity.Streaming;
import br.com.hyagorodrigues.movieflix.mapper.StreamingMapper;
import br.com.hyagorodrigues.movieflix.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService service;

    public StreamingController(StreamingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAll() {
        List<StreamingResponse> streamings = service.findAll().stream().map(StreamingMapper::toStreamingResponse).toList();

        return ResponseEntity.ok(streamings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id) {
        return service.findById(id).map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {
        Streaming streaming = StreamingMapper.toStreaming(request);
        Streaming save = service.save(streaming);

        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(save));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
