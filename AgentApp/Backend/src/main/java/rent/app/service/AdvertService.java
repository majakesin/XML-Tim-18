package rent.app.service;

import rent.app.dto.AdvertDto;
import rent.app.dto.AdvertFullDto;
import rent.app.dto.AdvertMiniDto;
import rent.app.dto.DTOEntity;
import rent.app.model.Advert;

import java.io.IOException;
import java.util.List;

public interface AdvertService {
    Advert saveAdvert(AdvertDto advertDto, String ownerEmail) throws IOException;
    List<AdvertMiniDto> getAllAdverts() throws IOException;
    AdvertFullDto getAdvert(Long id) throws IOException;
    List<DTOEntity> getAllMyAdverts(String clientEmail) throws IOException;

}
