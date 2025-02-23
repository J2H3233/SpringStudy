package hello.core.order;
import hello.core.annotaion.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    //테스트 용도
    @Getter
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    @Autowired                                                      //@Qualifier("mainDiscountPolicy") << QUALIFIER 사용 밑에는 에노테이션 생성
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}

//@Component
//public class OrderServiceImpl implements OrderService {
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
//}