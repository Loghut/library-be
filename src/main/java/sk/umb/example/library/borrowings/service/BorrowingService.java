package sk.umb.example.library.borrowings.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BorrowingService {
    private final AtomicLong lastIndex = new AtomicLong(0);

    private final Map<Long, BorrowingDetailDTO> BorrowingDatabase = new HashMap<>();
    public List<BorrowingDetailDTO> getAllBorrowings(){
        return new ArrayList<>(BorrowingDatabase.values());
    }

    public List<BorrowingDetailDTO> searchBorrowingByCustomer(String zakaznik){
        return BorrowingDatabase.values().stream().filter(dto -> zakaznik.equals(dto.getzakaznik())).toList();
    }

    public BorrowingDetailDTO getBorrowingById(Long BorrowingId){
        validateBorrowingExists(BorrowingId);
        return BorrowingDatabase.get(BorrowingId);
    }

    public Long CreateBorrowing(BorrowingRequestDTO BorrowingRequestDTO) {
        BorrowingDetailDTO BorrowingDetailDTO = mapToBorrowingDetailDTO(lastIndex.getAndIncrement(), BorrowingRequestDTO);

        BorrowingDatabase.put(BorrowingDetailDTO.getId(), BorrowingDetailDTO);
        return BorrowingDetailDTO.getId();
    }

    public static BorrowingDetailDTO mapToBorrowingDetailDTO(Long index, BorrowingRequestDTO BorrowingRequestDTO) {
        BorrowingDetailDTO dto = new BorrowingDetailDTO();
        dto.setId(index);
        dto.setzakaznik(BorrowingRequestDTO.getzakaznik());
        dto.setkniha(BorrowingRequestDTO.getkniha());

        return dto;
    }

    public void updateBorrowing(Long BorrowingId, BorrowingRequestDTO BorrowingRequestDTO) {
        validateBorrowingExists(BorrowingId);
        BorrowingDetailDTO BorrowingDetailDTO = BorrowingDatabase.get(BorrowingId);

        if (! Strings.isEmpty(BorrowingRequestDTO.getzakaznik())) {
            BorrowingDetailDTO.setzakaznik(BorrowingRequestDTO.getzakaznik());
        }

        if (! Strings.isEmpty(BorrowingRequestDTO.getkniha())) {
            BorrowingDetailDTO.setkniha(BorrowingRequestDTO.getkniha());
        }
    }

    private void validateBorrowingExists(Long BorrowingId) {
        if (! BorrowingDatabase.containsKey(BorrowingId)) {
            throw new IllegalArgumentException("BorrowingId: " + BorrowingId + " does not exist!");
        }
    }

    public void deleteBorrowing(Long BorrowingId) {BorrowingDatabase.remove(BorrowingId);}


}
