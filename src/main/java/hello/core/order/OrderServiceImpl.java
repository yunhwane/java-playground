package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /*
    단일 책임 원칙을 잘 지킨 설계 방식
    discountPolicy의 변경이 필요할 때 OrderServiceImpl의 코드를 변경하지 않아도 된다.
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        var member = memberRepository.findById(memberId);
        var discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
