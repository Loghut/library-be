package sk.umb.example.library.borrowings.controller;

import java.util.List;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.borrowings.service.BorrowingService;
import sk.umb.example.library.borrowings.service.BorrowingRequestDTO;
import sk.umb.example.library.borrowings.service.BorrowingDetailDTO;

@RestController
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("/api/borrowings")
    public List<BorrowingDetailDTO> searchBorrowingByCustomer(@RequestParam(required = false) String zakaznik){
        System.out.println("Search Borrowing called twice.");
        return Strings.isEmpty(zakaznik) ? borrowingService.getAllBorrowings()
                : borrowingService.searchBorrowingByCustomer(zakaznik);
    }

    @GetMapping("/api/{borrowingId}")
    public BorrowingDetailDTO getBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Get borrowing called. ");
        return borrowingService.getBorrowingById(borrowingId);
    }

    @PostMapping("/api/borrowings")
    public Long createBorrowing(@RequestBody BorrowingRequestDTO borrowingRequestDTO) {
        System.out.println("Create borrowing called: ");
        return borrowingService.CreateBorrowing(borrowingRequestDTO);
    }

    @PutMapping("/api/borrowings/{borrowingId}")
    public void updateBorrowing(@PathVariable Long borrowingId, @RequestBody BorrowingRequestDTO borrowingRequestDTO) {
        System.out.println("Update borrowing called: ID = " + borrowingId);
        borrowingService.updateBorrowing(borrowingId, borrowingRequestDTO);
    }

    @DeleteMapping("/api/borrowings/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId) {
        System.out.println("Delete borrowing called: ID = " + borrowingId);
        borrowingService.deleteBorrowing(borrowingId);
    }


}
