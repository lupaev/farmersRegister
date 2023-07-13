package ru.farmersregister.farmersregister.specification;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.farmersregister.farmersregister.dto.RequestDTO;
import ru.farmersregister.farmersregister.dto.RequestDTO.GlobalOperator;
import ru.farmersregister.farmersregister.dto.SearchRequestDTO;

@Component
public class SpecificationDTO<T> {


  public Specification<T> getSearchSpecification(Collection<SearchRequestDTO> searchRequestDTOs, RequestDTO.GlobalOperator globalOperator) {
    return (root, query, criteriaBuilder) -> {
      Collection<Predicate> predicates = new ArrayList<>();
      for (SearchRequestDTO requestDTO : searchRequestDTOs) {
        switch (requestDTO.getOperation()) {
          case EQUAL:
            Predicate equal = criteriaBuilder.equal(root.get(requestDTO.getColumn()),
                requestDTO.getValue());
            predicates.add(equal);
            break;
          case LIKE:
            Predicate like = criteriaBuilder.like(root.get(requestDTO.getColumn()),
                "%" + requestDTO.getValue() + "%");
            predicates.add(like);
            break;
          case IN:
            String[] split = requestDTO.getValue().split(",");
            Predicate in = root.get(requestDTO.getColumn()).in(Arrays.asList(split));
            predicates.add(in);
            break;
          case GT:
            Predicate gt = criteriaBuilder.greaterThan(root.get(requestDTO.getColumn()),
                requestDTO.getValue());
            predicates.add(gt);
            break;
          case LT:
            Predicate lt = criteriaBuilder.lessThan(root.get(requestDTO.getColumn()),
                requestDTO.getValue());
            predicates.add(lt);
            break;
          case BT:
            String[] splitBt = requestDTO.getValue().split(",");
            Predicate bt = criteriaBuilder.between(root.get(requestDTO.getColumn()),
                Long.parseLong(splitBt[0]), Long.parseLong(splitBt[1]));
            predicates.add(bt);
            break;
          default:
            throw new IllegalStateException("Неверно указан знак");

        }
      }

      if (globalOperator.equals(GlobalOperator.AND)) {
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
      } else {
        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
      }
    };
  }







}
