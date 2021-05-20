package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTimeAscComparator;
import ru.netology.repository.TicketRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private Ticket ticket1 = new Ticket(1, 4_500, "MOW", "KUF", 120);
    private Ticket ticket2 = new Ticket(2, 5_700, "MOW", "GOJ", 145);
    private Ticket ticket3 = new Ticket(3, 3_200, "MOW", "OGZ", 60);
    private Ticket ticket4 = new Ticket(4, 15_000, "MOW", "LED", 130);
    private Ticket ticket5 = new Ticket(5, 67_100, "MOW", "TXN", 210);
    private Ticket ticket6 = new Ticket(6, 3_900, "MOW", "KUF", 230);
    private Ticket ticket7 = new Ticket(7, 63_600, "MOW", "TXN", 240);
    private Ticket ticket8 = new Ticket(8, 59_800, "MOW", "TXN", 260);

    @BeforeEach
    public void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
    }

    @Test
    void shouldFindTicketsByTime() {
        TicketByTimeAscComparator comparator = new TicketByTimeAscComparator();
        Ticket[] actual = manager.findTicketsByTime("MOW", "KUF", comparator);
        Ticket[] expected = new Ticket[]{ticket1, ticket6};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldFindTicketsByTime2() {
        TicketByTimeAscComparator comparator = new TicketByTimeAscComparator();
        Ticket[] actual = manager.findTicketsByTime("MOW", "TXN", comparator);
        Ticket[] expected = new Ticket[]{ticket5, ticket7, ticket8};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldNotFindTicketsByTime() {
        TicketByTimeAscComparator comparator = new TicketByTimeAscComparator();
        Ticket[] actual = manager.findTicketsByTime("TXN", "ICN", comparator);
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldFindTicketsByPrice() {
        Ticket[] actual = manager.findTicketsByPrice("MOW", "OGZ");
        Ticket[] expected = new Ticket[]{ticket3};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldFindTicketsWithTheSameRoute() {
        Ticket[] actual = manager.findTicketsByPrice("MOW", "KUF");
        Ticket[] expected = new Ticket[]{ticket6, ticket1};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldFindTicketsWithTheSameRoute2() {
        Ticket[] actual = manager.findTicketsByPrice("MOW", "TXN");
        Ticket[] expected = new Ticket[]{ticket8, ticket7, ticket5};
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldNotFindTickets() {
        Ticket[] actual = manager.findTicketsByPrice("GOJ", "ICN");
        Ticket[] expected = new Ticket[0];
        assertArrayEquals(actual, expected);
    }
}