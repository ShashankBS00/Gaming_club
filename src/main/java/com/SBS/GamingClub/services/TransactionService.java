
@services
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;    
    @Autowired memberRepository memberRepository;
    @Autowired gameRepository gameRepository;   


    public Transaction playGame(playdto playdto) {
     Menber member = memberRepository.findById(playdto.getMemberId()).get();
        Game game = gameRepository.findById(playdto.getGameId()).get();
        Transaction transaction = new Transaction();
        transaction.setMember(member);      
        transaction.setGame(game);
        transaction.setPlayDate(new Date());
        transaction.setAmount(game.getAmount());
        return transactionRepository.save(transaction); 
        member.setBalance(member.getBalance() - game.getAmount());      
    }
}