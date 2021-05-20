package com.lczapparolli.avaliacao.goldenraspberyawards.data;

import java.util.List;

import com.lczapparolli.avaliacao.goldenraspberyawards.models.ProducerInterval;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ProducerIntervalRepository extends Repository<ProducerInterval, Long> {

    /**
     * Realiza a consulta dos produtores com menor intervalo entre duas premiações
     * @return Lista de produtores com o ano da vitória anterior e o ano da vitória seguinte
     */
    @Query(nativeQuery = true, value = "SELECT ID, PRODUCER, PREVIOUS_WIN, FOLLOWING_WIN, \"INTERVAL\" \n"
                                     + "FROM ( \n"
                                     + "    SELECT P.ID, P.NAME AS PRODUCER, N_FIRST.YEAR AS PREVIOUS_WIN, MIN(N_FOLLOW.YEAR) AS FOLLOWING_WIN, (MIN(N_FOLLOW.YEAR) - N_FIRST.YEAR) AS \"INTERVAL\", RANK() OVER(ORDER BY (MIN(N_FOLLOW.YEAR) - N_FIRST.YEAR)) AS \"RANK\" \n"
                                     + "    FROM PRODUCER P \n"
                                     + "    INNER JOIN NOMINEE_PRODUCER NP_FIRST ON NP_FIRST.PRODUCER_ID = P.ID \n"
                                     + "    INNER JOIN NOMINEE N_FIRST ON N_FIRST.ID = NP_FIRST.NOMINEE_ID AND N_FIRST.WINNER = TRUE \n"
                                     + "    INNER JOIN NOMINEE_PRODUCER NP_FOLLOW ON NP_FOLLOW.PRODUCER_ID = P.ID \n"
                                     + "    INNER JOIN NOMINEE N_FOLLOW ON N_FOLLOW.ID = NP_FOLLOW.NOMINEE_ID AND N_FOLLOW.WINNER = TRUE AND N_FOLLOW.YEAR > N_FIRST.YEAR \n"
                                     + "    GROUP BY P.ID, P.NAME, N_FIRST.YEAR \n"
                                     + ") TMP \n"
                                     + "WHERE TMP.RANK = 1 \n"
                                     + "ORDER BY PRODUCER")
    List<ProducerInterval> findAllWithMinInterval();

    /**
     * Realiza a consulta dos produtores com maior intervalo entre duas premiações
     * @return Lista de produtores com o ano da vitória anterior e o ano da vitória seguinte
     */
    @Query(nativeQuery = true, value = "SELECT ID, PRODUCER, PREVIOUS_WIN, FOLLOWING_WIN, \"INTERVAL\" \n"
                                     + "FROM ( \n"
                                     + "    SELECT P.ID, P.NAME AS PRODUCER, N_FIRST.YEAR AS PREVIOUS_WIN, MIN(N_FOLLOW.YEAR) AS FOLLOWING_WIN, (MIN(N_FOLLOW.YEAR) - N_FIRST.YEAR) AS \"INTERVAL\", RANK() OVER(ORDER BY (MIN(N_FOLLOW.YEAR) - N_FIRST.YEAR) DESC) AS \"RANK\" \n"
                                     + "    FROM PRODUCER P \n"
                                     + "    INNER JOIN NOMINEE_PRODUCER NP_FIRST ON NP_FIRST.PRODUCER_ID = P.ID \n"
                                     + "    INNER JOIN NOMINEE N_FIRST ON N_FIRST.ID = NP_FIRST.NOMINEE_ID AND N_FIRST.WINNER = TRUE \n"
                                     + "    INNER JOIN NOMINEE_PRODUCER NP_FOLLOW ON NP_FOLLOW.PRODUCER_ID = P.ID \n"
                                     + "    INNER JOIN NOMINEE N_FOLLOW ON N_FOLLOW.ID = NP_FOLLOW.NOMINEE_ID AND N_FOLLOW.WINNER = TRUE AND N_FOLLOW.YEAR > N_FIRST.YEAR \n"
                                     + "    GROUP BY P.ID, P.NAME, N_FIRST.YEAR \n"
                                     + ") TMP \n"
                                     + "WHERE TMP.RANK = 1 \n"
                                     + "ORDER BY PRODUCER")
    List<ProducerInterval> findAllWithMaxInterval();
    
}