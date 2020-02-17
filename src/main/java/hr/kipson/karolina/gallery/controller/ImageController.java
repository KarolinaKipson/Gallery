package hr.kipson.karolina.gallery.controller;

import hr.kipson.karolina.gallery.LoggerImage;
import hr.kipson.karolina.gallery.model.Image;
import hr.kipson.karolina.gallery.model.SiteUser;
import hr.kipson.karolina.gallery.repository.ImageRepository;
import hr.kipson.karolina.gallery.repository.SiteUserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.core.io.ResourceLoader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ImageController {


    private Logger log = LoggerFactory.getLogger(ImageController.class);
    private SiteUserRepository userRepository;
    private ImageRepository imageRepository;
    private Path rootLocation;

    public ImageController(SiteUserRepository userRepository, Path rootLocation, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.rootLocation = rootLocation;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model, Principal principal) throws Exception {

        if (principal == null) {
            return "redirect:/find";
        }

        SiteUser user = userRepository.findByName(principal.getName()).orElseThrow(() -> new Exception());

        List<String> stringss = user.getImageList().stream()
                .map(image -> this.rootLocation.resolve(image.getName()))
                .map(path -> MvcUriComponentsBuilder
                        .fromMethodName(ImageController.class, "serveFile", path.getFileName().toString()).build()
                        .toString())
                .collect(Collectors.toList());


        model.addAttribute("files", stringss);

        return "upload";
    }

    @GetMapping(value="/files/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

        Path file = this.rootLocation.resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        return ResponseEntity
                .ok()
                .body(resource);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
                                   Principal principal) throws Exception {

        if (file.getSize() == 0) {
            return "redirect:/";
        }

        String uuid = UUID.randomUUID().toString();

        String imagePath = this.rootLocation.resolve(uuid + ".jpg").toString();

        SiteUser user = userRepository.findByName(principal.getName()).orElseThrow(() -> new Exception());

        Set<Image> stringList = user.getImageList();
        stringList.add(new Image(imagePath));
        Files.copy(file.getInputStream(), this.rootLocation.resolve(imagePath));

        userRepository.save(user);
        LoggerImage logger = LoggerImage.getInstance();
        logger.logUserUploadImage(user);
        return "redirect:/";

    }

    @GetMapping("/find")
    public String findPhotos(Model model) {
        return "findphoto";
    }

    @GetMapping("/search")
    public String findPhotos(@RequestParam("name") String name, Model model)  {

        SiteUser user;
        try {
            user = userRepository.findByName(name).orElseThrow(Exception::new);
        } catch (Exception e) {
            return "redirect:/find";
        }

        List<String> userImages = user.getImageList().stream()
                .map(image -> this.rootLocation.resolve(image.getName()))
                .map(path -> MvcUriComponentsBuilder
                        .fromMethodName(ImageController.class, "serveFile", path.getFileName().toString()).build()
                        .toString())
                .collect(Collectors.toList());

        model.addAttribute("files", userImages);
        model.addAttribute("user", user.getName());

        return "findphoto";

    }

    @RequestMapping("/delete")
    public String findPhotos(Principal principal, @RequestParam("text") String text, String string) throws Exception {

        SiteUser user = userRepository.findByName(principal.getName()).orElseThrow(() -> new Exception());

        text = text.substring(text.lastIndexOf("/"));
        text= text.substring(1);
        text = this.rootLocation + "\\" + text;

        Image image = imageRepository.findByName(text);

        user.getImageList().remove(image);

        userRepository.save(user);

        LoggerImage logger = LoggerImage.getInstance();
        logger.logUserDeleteImage(user, image);
        log.info("Image deleted from system.");
        return "redirect:/";

    }
}
