package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // this function we created to use again and again to assign values to the PostDto
    public PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        dto.setTitle(post.getTitle());
        return dto;
    }

    // this function we created to use again and again to assign values to the PostDto
    public Post mapToEntity(PostDto dto){
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());
        return post;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        //insert data into table
        Post post = mapToEntity(postDto);
        Post savePost = postRepository.save(post);
        //to show the dto data to frontend give this copy data from post -> dto
        PostDto dto = mapToDto(savePost);
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        /*
        * orElseThrow -> this will thow error if function throws error
        * but if we only does this then it will thow our error + other unwanted error
        *
        * */
        Post post = postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found id:"+id)
                );
        // assigning all id record to the dto
        PostDto dto = mapToDto(post);
        return dto;
    }

    // fetching all records  and assigning it to the allRecord
    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        //if inserting sortDir is 'ASC' then sort to ascending else descending
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //Pageable used for showing limited records to frontend
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> allRecord = pagePost.getContent();
        // assigining allRecords to the Dto via FUNCTION mapToDto()
        List<PostDto> collect = allRecord.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return collect;
    }

}
